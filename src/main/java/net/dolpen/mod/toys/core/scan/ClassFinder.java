package net.dolpen.mod.toys.core.scan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassFinder {

  private static final ClassScanner SCANNER = new CombinedClassScanner();

  private static final Map<String, List<Class<?>>> CACHE = new HashMap<>();

  public static List<Class<?>> list(Class<?> clazz) {
    return list(clazz.getPackageName());
  }

  public static List<Class<?>> list(String packageName) {
    if (CACHE.containsKey(packageName)) return CACHE.get(packageName);
    List<Class<?>> classes = SCANNER.scan(packageName);
    CACHE.put(packageName, classes);
    return classes;
  }
}
