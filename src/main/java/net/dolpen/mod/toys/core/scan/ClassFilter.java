package net.dolpen.mod.toys.core.scan;

import java.util.function.Predicate;

public class ClassFilter {

  public static Predicate<Class<?>> hasInterfaceOf(final Class<?> interfaceClass) {
    if (!interfaceClass.isInterface() || interfaceClass.isAnnotation())
      throw new IllegalArgumentException(
          "Class " + interfaceClass.getName() + " is not an interface");
    return clazz -> {
      if (clazz.isInterface() || clazz.isAnnotation()) return false;
      return interfaceClass.isAssignableFrom(clazz);
    };
  }
}
