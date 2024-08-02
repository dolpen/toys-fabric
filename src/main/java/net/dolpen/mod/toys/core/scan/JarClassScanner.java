package net.dolpen.mod.toys.core.scan;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;

class JarClassScanner extends ClassScanner {
  @Override
  public List<Class<?>> scan(String packageName) {
    String resourceName = toResourceName(packageName);
    ClassLoader classLoader = getClassLoader();
    final URL root = Objects.requireNonNull(getClassLoader().getResource(resourceName));
    try (JarFile jarFile = ((JarURLConnection) root.openConnection()).getJarFile()) {
      return Collections.list(jarFile.entries()).stream()
          .map(ZipEntry::getName)
          .filter(name -> name.startsWith(resourceName))
          .filter(name -> name.endsWith(".class"))
          .filter(name -> !name.contains("$"))
          .map(ClassScanner::toPackageName)
          .map(name -> name.replaceAll(".class$", ""))
          .map(fullName -> uncheckCall(() -> classLoader.loadClass(fullName)))
          .collect(Collectors.toList());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
