package net.dolpen.mod.toys.feature.hud;

import java.awt.*;
import net.dolpen.mod.toys.bridge.data.Axis;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;

public class PitchComponent implements HudComponent {
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
    String content = String.format("%3d", pitch);

    int fontSize = client.font.width(" ");
    int xPos = (fontSize * 6 - client.font.width(content)) + (guiGraphics.guiWidth() >> 1);
    int yPos = -client.font.lineHeight + (guiGraphics.guiHeight() >> 1);
    int color = (pitch >= 0 ? Color.GREEN : Color.RED).getRGB();
    guiGraphics.drawString(client.font, content, xPos, yPos, color | (int) 1 << 24);
  }
}
