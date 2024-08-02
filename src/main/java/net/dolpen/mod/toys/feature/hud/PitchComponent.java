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
  private final net.dolpen.mod.toys.model.geometry.Point offset;

  public PitchComponent(Minecraft client) {
    this.client = client;
    this.offset =
        new Point(TextRenderer.offsetUnderScore(client.font, LENGTH), client.font.lineHeight);
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
    TextRenderer.render(
        guiGraphics,
        offset.join(TextRenderer.center(guiGraphics)),
        String.format("%3d", pitch),
        client.font,
        pitch >= 0 ? Color.GREEN : Color.RED);
  }
}
