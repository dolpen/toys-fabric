package net.dolpen.mod.toys.feature.hud;

import net.dolpen.mod.toys.bridge.data.Axis;
import net.dolpen.mod.toys.bridge.render.TextRenderer;
import net.dolpen.mod.toys.model.geometry.Point;
import net.dolpen.mod.toys.model.render.Color;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;

public class PitchComponent implements HudComponent {
  private static final int LENGTH = 6;
  private final Minecraft client;

  public PitchComponent(Minecraft client) {
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
    int pitch = Axis.getPitch(player);
    Point offset =
        new Point(TextRenderer.offsetUnderScore(client.font, LENGTH), client.font.lineHeight);
    TextRenderer.render(
        guiGraphics,
        offset.join(TextRenderer.center(guiGraphics)),
        String.format("%3d", pitch),
        client.font,
        pitch >= 0 ? Color.GREEN : Color.RED);
  }
}
