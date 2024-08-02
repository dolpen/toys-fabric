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
          .map(Path::toFile)
          .filter(File::isFile)
          .map(File::getAbsolutePath)
          .filter(path -> !path.contains("$"))
          .map(path -> path.replaceAll("^.*" + resourceName, resourceName))
          .map(path -> path.replaceAll(".class$", ""))
          .map(ClassScanner::toPackageName)
          .map(fullName -> uncheckCall(() -> Class.forName(fullName)))
          .collect(Collectors.toList());
    } catch (IOException | URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}
