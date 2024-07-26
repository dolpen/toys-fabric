package net.dolpen.mod.toys.feature.hud;

import java.util.List;
import net.dolpen.mod.toys.core.di.Dicon;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class Hud {

  static final List<HudComponent> COMPONENTS =
      List.of(Dicon.getInstance(SpeedComponent.class), Dicon.getInstance(PitchComponent.class));

  public static void execute() {
    COMPONENTS.forEach(
        component -> {
          HudRenderCallback.EVENT.register(component::accept);
        });
  }
}
