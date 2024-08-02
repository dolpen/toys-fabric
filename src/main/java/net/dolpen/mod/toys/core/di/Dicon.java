package net.dolpen.mod.toys.core.di;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dicon {

  private static final Logger LOG = LoggerFactory.getLogger(Dicon.class);

  private static final Map<String, Constructor<?>> CONSTRUCTORS = new HashMap<>();
  private static final Map<String, Supplier<?>> SUPPLIERS = new HashMap<>();
  private static final Map<String, Object> INSTANCES = new HashMap<>();

  public static void init(Class<?> base) {
    AutoInjector.process(base);
    (new ModModuleProcessor(base)).process();
  }

  public static void register(Class<?> clazz) {
    Stream.of(clazz.getConstructors())
        .filter(constructor -> constructor.getParameterCount() > 0)
        .forEach(nullableConstructor -> CONSTRUCTORS.put(clazz.getName(), nullableConstructor));
  }

  public static <T> void registerSupplier(Class<T> clazz, Supplier<T> supplier) {
    SUPPLIERS.put(clazz.getName(), supplier);
  }

  public static <T> void registerValue(Class<T> clazz, T value) {
    INSTANCES.put(clazz.getName(), value);
  }

  public static <T> T getInstance(Class<T> clazz) {
    try {
      return createInstance(clazz);
    } catch (Exception e) {
      LOG.error("Failed to getInstance. message={}, cause={}", e.getMessage(), e.getCause());
      throw new RuntimeException(e);
    }
  }

  @SuppressWarnings("unchecked")
  private static <T> T createInstance(Class<T> clazz)
      throws InvocationTargetException,
          InstantiationException,
          IllegalAccessException,
          NoSuchMethodException {
    final String key = clazz.getName();
    if (INSTANCES.containsKey(key)) {
      return (T) INSTANCES.get(key);
    }
    if (SUPPLIERS.containsKey(key)) {
      T instance = (T) SUPPLIERS.get(key).get();
      INSTANCES.put(key, instance);
      return instance;
    }
    Constructor<?> constructor =
        CONSTRUCTORS.containsKey(key) ? CONSTRUCTORS.get(key) : clazz.getDeclaredConstructor();
    T instance =
        (T)
            constructor.newInstance(
                Stream.of(constructor.getParameterTypes()).map(Dicon::getInstance).toArray());
    INSTANCES.put(key, instance);
    return instance;
  }
}
