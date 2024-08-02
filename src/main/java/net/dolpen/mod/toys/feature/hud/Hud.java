package net.dolpen.mod.toys.feature.hud;

import java.util.List;
import net.dolpen.mod.toys.core.stereotype.ModModule;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

@SuppressWarnings("unused")
public class Hud implements ModModule {

  private final List<HudComponent> components;

  public Hud(SpeedComponent speedComponent, PitchComponent pitchComponent) {
    components = List.of(speedComponent, pitchComponent);
  }

  @Override
  public void init() {
    components.forEach(component -> HudRenderCallback.EVENT.register(component::accept));
  }
}
