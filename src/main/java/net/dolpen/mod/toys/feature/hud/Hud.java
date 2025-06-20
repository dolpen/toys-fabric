package net.dolpen.mod.toys.feature.hud;

import java.util.List;
import net.dolpen.mod.toys.Main;
import net.dolpen.mod.toys.core.stereotype.ModModule;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.resources.ResourceLocation;

@SuppressWarnings("unused")
public class Hud implements ModModule {

  private final List<HudComponent> components;
  private final String NAME = "hud";

  public Hud(SpeedComponent speedComponent, PitchComponent pitchComponent) {
    components = List.of(speedComponent, pitchComponent);
  }

  @Override
  public void init() {
    HudElementRegistry.addLast(
        ResourceLocation.fromNamespaceAndPath(Main.NAMESPACE, NAME),
        (guiGraphics, deltaTracker) ->
            components.forEach(component -> component.accept(guiGraphics, deltaTracker)));
  }
}
