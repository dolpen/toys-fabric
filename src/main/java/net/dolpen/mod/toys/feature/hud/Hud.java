package net.dolpen.mod.toys.feature.hud;

import java.util.List;
import net.dolpen.mod.toys.Main;
import net.dolpen.mod.toys.core.stereotype.ModModule;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.resources.Identifier;

@SuppressWarnings("unused")
public class Hud implements ModModule {

  private final List<HudComponent> components;

  @SuppressWarnings("FieldCanBeLocal")
  private final String NAME = "hud";

  public Hud(
      SpeedComponent speedComponent, PitchComponent pitchComponent, BoostComponent boostComponent) {
    components = List.of(speedComponent, pitchComponent, boostComponent);
  }

  @Override
  public void init() {
    HudElementRegistry.attachElementAfter(
        VanillaHudElements.CROSSHAIR,
        Identifier.fromNamespaceAndPath(Main.NAMESPACE, NAME),
        (guiGraphics, deltaTracker) ->
            components.forEach(component -> component.accept(guiGraphics, deltaTracker)));
  }
}
