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

public class PitchComponent implements HudComponent {

  private final Minecraft client;
  private final Rect rect;

  private static final int LENGTH = 5;

  public PitchComponent(Minecraft client) {
    this.client = client;
    Font font = client.font;
    this.rect =
        new Rect(
            new Point(0, -(int) (font.lineHeight * 1.2)),
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
    int pitch = Axis.getPitch(player);

    Renderer renderer = new Renderer(guiGraphics);
    renderer.accept(
        new StringTask(
            String.format("%3d", pitch),
            client.font,
            pitch >= 0 ? Color.GREEN : Color.RED,
            this.rect.offset(renderer.center()),
            HorizontalAlignment.RIGHT,
            VerticalAlignment.CENTER));
  }
}
