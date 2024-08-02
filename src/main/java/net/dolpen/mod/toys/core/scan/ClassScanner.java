package net.dolpen.mod.toys.core.scan;

import java.util.List;
import java.util.concurrent.Callable;

abstract class ClassScanner {
  private static final char RESOURCE_DELIMITER = '/';
  private static final char PACKAGE_DELIMITER = '.';

  abstract List<Class<?>> scan(String packageName);

  protected static <T> T uncheckCall(Callable<T> callable) {
    try {
      return callable.call();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  protected static String toResourceName(final String packageName) {
    return packageName.replace(PACKAGE_DELIMITER, RESOURCE_DELIMITER);
  }

  protected static String toPackageName(final String packageName) {
    return packageName.replace(RESOURCE_DELIMITER, PACKAGE_DELIMITER);
  }

  protected static ClassLoader getClassLoader() {
    return Thread.currentThread().getContextClassLoader();
  }
}
