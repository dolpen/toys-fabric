/**
 * gradle.properties から特定キーの文字列を解決する
 */
fun propertyAsString(name: String): String {
    return if (project.hasProperty(name)) project.properties[name] as String
    else "__${name}__"
}

/**
 * org.gradle.api.provider.Provider<String> から取り出すだけ
 */
fun provideAsString(provider: Provider<String>): String = provider.getOrElse("N/A")

/**
 * 各種バージョン
 */

// dependency versions from libs.versions.toml
val mcVersion: String = provideAsString(libs.versions.minecraft)
val loaderVersion: String = provideAsString(libs.versions.fabricLoader)
val apiVersion: String = provideAsString(libs.versions.fabricApi)

// mod settings from gradle.properties
val modId: String = propertyAsString("mod_id")
val modGroup: String = propertyAsString("mod_group")
val modName: String = propertyAsString("mod_name")
val modVersion: String = propertyAsString("mod_version")

/**
 * バージョン表示
 */
fun createVersionString(): String {
    val isRelease = project.hasProperty("build.release")
    val buildId = System.getenv("GITHUB_RUN_NUMBER")
    val versionInfo = "${modVersion}${if (isRelease) "" else "-SNAPSHOT"}"
    val mcInfo = "+mc${mcVersion}"
    val buildInfo = if (isRelease) "" else {
        if (buildId != null) "-build.${buildId}" else "-local"
    }
    return "${versionInfo}${mcInfo}${buildInfo}"
}

/**
 * プラグイン整理
 */
plugins {
    // environment automation plugin for mod development
    alias(libs.plugins.fabric.loom)
    // code format plugin, independent of IDE
    alias(libs.plugins.spotless)
}

/**
 * 依存関係整理
 */
dependencies {
    minecraft(libs.minecraft)
    mappings(loom.officialMojangMappings())
    modImplementation(libs.bundles.fabric)
}


/**
 * プロジェクト基本設定
 */
base {
    archivesName = modId
    group = modGroup
    version = createVersionString()
}

/**
 * mod 開発環境構築設定
 */
loom {
    mixin { // Experimental
        // refmap のファイル名は modId 使う
        defaultRefmapName = "${modId}.refmap.json"
    }
    // アクセス修飾子を超えてminecraftの内部にアクセスするリフレクション黒魔術用設定ファイル
    // https://www.fabricmc.net/wiki/ja:tutorial:accesswideners
    accessWidenerPath = file("src/main/resources/${modId}.accesswidener")
}

/**
 * コンパイルオプション
 */
java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

/**
 * フォーマッタ
 */
spotless {
    java {
        googleJavaFormat(provideAsString(libs.versions.googleJavaFormat)).reflowLongStrings()
        formatAnnotations()
    }
}


tasks {
    create("echoVersion") {
        println(createVersionString())
        println("$modName - $modGroup:$modId:$modVersion")
    }
    processResources {
        // fabric.mod.json の書き換え
        // 最低限のバージョン依存関係などはバージョンカタログから持ってくる
        // 起動用クラスなどの情報も設定ファイルから補完
        filesMatching("fabric.mod.json") {
            expand(
                mapOf(
                    "version" to project.version,
                    "id" to modId,
                    "name" to modName,
                    "mc_version" to mcVersion,
                    "loader_version" to loaderVersion,
                    "main" to "${modGroup}.${propertyAsString("mod_main")}"
                )
            )
        }
    }
}


