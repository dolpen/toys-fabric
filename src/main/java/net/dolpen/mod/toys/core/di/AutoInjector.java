package net.dolpen.mod.toys.core.di;

import java.util.Arrays;
import java.util.function.Predicate;
import net.dolpen.mod.toys.core.scan.ClassFinder;
import net.dolpen.mod.toys.core.stereotype.Component;

class AutoInjector {

  private static final Predicate<Class<?>> isTarget =
      clazz ->
          Arrays.stream(clazz.getInterfaces())
              .anyMatch(interfaceClass -> interfaceClass.isAssignableFrom(Component.class));

  public static void process(Class<?> base) {
    ClassFinder.list(base).stream().filter(isTarget).forEach(Dicon::register);
  }
}
