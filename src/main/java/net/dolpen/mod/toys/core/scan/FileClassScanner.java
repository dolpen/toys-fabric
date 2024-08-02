package net.dolpen.mod.toys.core.scan;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class FileClassScanner extends ClassScanner {

  @Override
  public List<Class<?>> scan(String packageName) {
    final String resourceName = toResourceName(packageName);
    final URL root = Objects.requireNonNull(getClassLoader().getResource(resourceName));
    try (Stream<Path> files = Files.walk(Paths.get(root.toURI()))) {

      return files
          .filter(Files::isRegularFile)
          .map(Path::toString)
          .filter(path -> !path.contains("$"))
          .map(FileClassScanner::fromFilePath)
          .map(path -> path.replaceAll("^.*" + packageName, packageName))
          .map(path -> path.replaceAll(".class$", ""))
          .map(fullName -> uncheckCall(() -> Class.forName(fullName)))
          .collect(Collectors.toList());
    } catch (IOException | URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  private static String fromFilePath(final String filePath) {
    return filePath.replace(File.separatorChar, PACKAGE_DELIMITER);
  }
}
