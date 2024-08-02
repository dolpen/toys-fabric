package net.dolpen.mod.toys.core.scan;

import java.util.List;
import java.util.Objects;

class CombinedClassScanner extends ClassScanner {

  private static final ClassScanner FILE = new FileClassScanner();
  private static final ClassScanner JAR = new JarClassScanner();
  private static final ClassScanner EMPTY =
      new ClassScanner() {
        @Override
        public List<Class<?>> scan(String packageName) {
          return List.of();
        }
      };

  @Override
  public List<Class<?>> scan(String packageName) {
    ClassScanner internal =
        switch (Objects.requireNonNull(
                Thread.currentThread()
                    .getContextClassLoader()
                    .getResource(ClassScanner.toResourceName(packageName)))
            .getProtocol()) {
          case "file" -> FILE;
          case "jar" -> JAR;
          default -> EMPTY;
        };
    return internal.scan(packageName);
  }
}
