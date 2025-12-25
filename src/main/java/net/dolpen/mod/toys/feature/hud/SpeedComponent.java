package net.dolpen.mod.toys.feature.hud;

import net.dolpen.mod.toys.bridge.data.Axis;
import net.dolpen.mod.toys.bridge.render.TextRenderer;
import net.dolpen.mod.toys.model.geometry.Point;
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
    Point offset =
        new Point(
            TextRenderer.offsetUnderScore(client.font, LENGTH),
            (int) (client.font.lineHeight * 0.2));
    TextRenderer.renderRightAlignment(
        guiGraphics,
        offset.join(TextRenderer.center(guiGraphics)),
        String.format("%2.1f", Axis.getSpeed(player, deltaTracker)),
        client.font,
        Color.WHITE);
  }
}
