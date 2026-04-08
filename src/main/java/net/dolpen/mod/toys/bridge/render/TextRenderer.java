package net.dolpen.mod.toys.bridge.render;

import net.dolpen.mod.toys.model.geometry.Point;
import net.dolpen.mod.toys.model.render.Color;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class TextRenderer {

  public static void render(
      GuiGraphicsExtractor guiGraphics, Point p, String content, Font font, Color color) {
    guiGraphics.text(font, content, p.x(), p.y(), color.value());
  }

  public static void renderRightAlignment(
      GuiGraphicsExtractor guiGraphics, Point p, String content, Font font, Color color) {
    render(guiGraphics, p.withX(-font.width(content)), content, font, color);
  }

  public static int offsetUnderScore(Font font, int length) {
    return font.width("_") * length;
  }

  public static Point center(GuiGraphicsExtractor guiGraphics) {
    return new Point(guiGraphics.guiWidth() >> 1, guiGraphics.guiHeight() >> 1);
  }
}
