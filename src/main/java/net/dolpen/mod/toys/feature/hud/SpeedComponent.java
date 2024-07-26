package net.dolpen.mod.toys.feature.hud;

import java.awt.*;
import net.dolpen.mod.toys.bridge.data.Axis;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;

public class SpeedComponent implements HudComponent {
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
    String content = Axis.getSpeed(player);
    int fontSize = client.font.width(" ");
    int xPos = (fontSize * 13 - client.font.width(content)) + (guiGraphics.guiWidth() >> 1);
    int yPos = (int) (client.font.lineHeight * 0.2) + (guiGraphics.guiHeight() >> 1);
    guiGraphics.drawString(client.font, content, xPos, yPos, 0xFFFFFF | (int) 1 << 24);
  }
}
