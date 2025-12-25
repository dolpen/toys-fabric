package net.dolpen.mod.toys.feature.hud;

import net.dolpen.mod.toys.bridge.data.Axis;
import net.dolpen.mod.toys.feature.hud.task.HorizontalAlignment;
import net.dolpen.mod.toys.feature.hud.task.StringTask;
import net.dolpen.mod.toys.feature.hud.task.VerticalAlignment;
import net.dolpen.mod.toys.feature.hud.util.Renderer;
import net.dolpen.mod.toys.model.geometry.Dimension;
import net.dolpen.mod.toys.model.geometry.Point;
import net.dolpen.mod.toys.model.geometry.Rect;
import net.dolpen.mod.toys.model.render.Color;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;

public class SpeedComponent implements HudComponent {

  private final Minecraft client;
  private final Rect rect;

  private static final int LENGTH = 10;

  public SpeedComponent(Minecraft client) {
    this.client = client;
    Font font = client.font;
    this.rect =
        new Rect(
            new Point(0, font.lineHeight / 5),
            new Dimension(font.width("_") * LENGTH, font.lineHeight));
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
    Renderer renderer = new Renderer(guiGraphics);
    renderer.accept(
        new StringTask(
            String.format("%2.1f", Axis.getSpeed(player, deltaTracker)),
            client.font,
            Color.WHITE,
            this.rect.offset(renderer.center()),
            HorizontalAlignment.RIGHT,
            VerticalAlignment.CENTER));
  }
}
