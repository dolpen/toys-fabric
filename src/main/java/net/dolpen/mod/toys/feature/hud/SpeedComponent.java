package net.dolpen.mod.toys.feature.hud;

import net.dolpen.mod.toys.bridge.data.Axis;
import net.dolpen.mod.toys.bridge.render.Fonts;
import net.dolpen.mod.toys.bridge.render.Renderer;
import net.dolpen.mod.toys.feature.hud.task.HorizontalAlignment;
import net.dolpen.mod.toys.feature.hud.task.StringTask;
import net.dolpen.mod.toys.feature.hud.task.VerticalAlignment;
import net.dolpen.mod.toys.model.geometry.Dimension;
import net.dolpen.mod.toys.model.geometry.Rect;
import net.dolpen.mod.toys.model.render.Color;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;

public record SpeedComponent(Minecraft client) implements HudComponent {

  private static final int LENGTH = 10;

  @Override
  public void accept(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
    LocalPlayer player = client.player;
    if (player == null) {
      return;
    }
    if (!Axis.isFlying(player)) {
      return;
    }

    Dimension fontMetrics = Fonts.singleChar(client.font);
    Renderer renderer = new Renderer(guiGraphics);
    renderer.accept(
        new StringTask(
            String.format("%2.1f", Axis.getSpeed(player, deltaTracker)),
            client.font,
            Color.WHITE,
            new Rect(renderer.center().with(fontMetrics.width(), 1), fontMetrics.mulW(LENGTH)),
            HorizontalAlignment.RIGHT,
            VerticalAlignment.CENTER));
  }
}
