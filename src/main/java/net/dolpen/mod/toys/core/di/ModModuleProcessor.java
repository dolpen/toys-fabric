package net.dolpen.mod.toys.core.di;

import net.dolpen.mod.toys.core.stereotype.ModModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModModuleProcessor extends Processor<ModModule> {

  private static final Logger LOG = LoggerFactory.getLogger(ModModuleProcessor.class);

  ModModuleProcessor(Class<?> base) {
    super(base, ModModule.class);
  }

  @Override
  void processEach(Class<ModModule> input) {
    LOG.debug("loading module : {}", input.getSimpleName());
    Dicon.getInstance(input).init();
  }
}
