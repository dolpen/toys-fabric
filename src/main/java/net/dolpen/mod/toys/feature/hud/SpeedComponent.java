package net.dolpen.mod.toys.feature.hud;

import net.dolpen.mod.toys.bridge.data.Axis;
import net.dolpen.mod.toys.bridge.render.TextRenderer;
import net.dolpen.mod.toys.model.geometry.Point;
import net.dolpen.mod.toys.model.render.Color;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;

public class SpeedComponent implements HudComponent {

  private static final int LENGTH = 10;

  private final Minecraft client;

  public SpeedComponent(Minecraft client) {
    this.client = client;
  }

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
        Axis.getSpeed(player),
        client.font,
        Color.WHITE);
  }
}
