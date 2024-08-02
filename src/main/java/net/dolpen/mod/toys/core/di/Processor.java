package net.dolpen.mod.toys.core.di;

import java.util.Arrays;
import net.dolpen.mod.toys.core.scan.ClassFinder;

public abstract class Processor<I> {

  protected final Class<?> base;
  protected final Class<I> interfaceClass;

  Processor(Class<?> base, Class<I> interfaceClass) {
    this.base = base;
    this.interfaceClass = interfaceClass;
  }

  @SuppressWarnings("unchecked")
  protected void process() {
    ClassFinder.list(base).stream()
        .filter(
            clazz ->
                Arrays.stream(clazz.getInterfaces())
                    .anyMatch(interfaceClass -> interfaceClass.isAssignableFrom(interfaceClass)))
        .map(c -> (Class<I>) c)
        .forEach(this::processEach);
  }

  abstract void processEach(Class<I> input);
}
