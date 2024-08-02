package net.dolpen.mod.toys.core.di;

import java.util.function.Predicate;
import net.dolpen.mod.toys.core.scan.ClassFilter;
import net.dolpen.mod.toys.core.scan.ClassFinder;
import net.dolpen.mod.toys.core.stereotype.Component;

class AutoInjector {

  private static final Predicate<Class<?>> FILTER = ClassFilter.hasInterfaceOf(Component.class);

  public static void process(Class<?> base) {
    ClassFinder.list(base).stream().filter(FILTER).forEach(Dicon::register);
  }
}
