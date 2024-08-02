package net.dolpen.mod.toys;

import net.dolpen.mod.toys.core.di.Dicon;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.Minecraft;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
public class Main implements ClientModInitializer {

  private static final Logger LOG = LoggerFactory.getLogger(Main.class);

  @Override
  public void onInitializeClient() {
    LOG.info("Trigger on InitializationClient");
    Dicon.registerValue(Minecraft.class, Minecraft.getInstance());
    Dicon.init(Main.class);
  }
}
