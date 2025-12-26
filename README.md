<img src="src/main/resources/assets/toys/icon.png" width="100" alt="toys" />

# Toys (for Fabric)

主に個人向けのクライアント向け追加機能を実装しています

## 機能

* エリトラ使用中のHUD拡張
  * カメラ仰角表示
    * 主にエンド島周辺の虚無空間で[空間識失調](https://ja.wikipedia.org/wiki/%E7%A9%BA%E9%96%93%E8%AD%98%E5%A4%B1%E8%AA%BF)を防止します
    * 惰性飛行の場合は若干マイナス気味調整が便利です
  * 対地速度表示
    * X-Z平面上の速度です。平面移動の効率化に使ってください

## 導入

[GitHub release](https://github.com/dolpen/toys-fabric/releases) から成果物がDLできます

## 開発

### 前提

- OpenJDK 21 ([Eclipse Temurin](https://adoptium.net/) がおすすめです)
- Gradle > 9.2.x (付属のラッパーを使うのが楽です)
- その他依存ライブラリについてはすべて Gradle のバージョンカタログに記載しているので、適宜調整してください

### よく使うGradleタスク

Gradle IDE 統合 (IntelliJ など) を経由して実行するのをおすすめします

* `genSources` : Minecraftの本体クラスを復元します
  * 公式マッピングを使うため、Yarnなど他マッピングを使ったコードを参照する場合は注意してください
* `spotlessApply` : コードを可能な限り自動でフォーマットします
    * IDEなどに依存しないように Gradle プラグインを使っています
    * [google-java-format](https://github.com/google/google-java-format) を使っています
* `build` : 成果物を作ります
    * `build/libs` にアーティファクトが出力されます
