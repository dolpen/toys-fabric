package net.dolpen.mod.toys;

import net.dolpen.mod.toys.core.di.Dicon;
import net.dolpen.mod.toys.feature.hud.Hud;
import net.dolpen.mod.toys.feature.hud.PitchComponent;
import net.dolpen.mod.toys.feature.hud.SpeedComponent;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.Minecraft;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ClientModInitializer {

  private static final Logger log = LoggerFactory.getLogger(Main.class);

  @Override
  public void onInitializeClient() {
    log.info("Trigger on InitializationClient");
    Dicon.registerValue(Minecraft.class, Minecraft.getInstance());
    Dicon.register(SpeedComponent.class);
    Dicon.register(PitchComponent.class);
    Hud.execute();
  }
}
