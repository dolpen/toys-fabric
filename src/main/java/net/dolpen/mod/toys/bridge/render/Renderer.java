package net.dolpen.mod.toys.bridge.render;

import net.dolpen.mod.toys.feature.hud.task.RectTask;
import net.dolpen.mod.toys.feature.hud.task.StringTask;
import net.dolpen.mod.toys.model.geometry.Dimension;
import net.dolpen.mod.toys.model.geometry.Point;
import net.dolpen.mod.toys.model.geometry.Rect;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;

public class Renderer {

  final GuiGraphics graphics;

  public Renderer(GuiGraphics g) {
    this.graphics = g;
  }

  public Point center() {
    return new Point(graphics.guiWidth() >> 1, graphics.guiHeight() >> 1);
  }

  public void accept(StringTask task) {
    Rect box = task.box();
    Point topLeft = box.point();
    Dimension size = box.dimension();
    Font font = task.font();
    int x =
        topLeft.x() + task.horizontalAlignment().op.apply(size.width(), font.width(task.content()));
    int y = topLeft.y() + task.horizontalAlignment().op.apply(size.height(), font.lineHeight);
    graphics.drawString(task.font(), task.content(), task.color().value(), x, y);
  }

  public void accept(RectTask task) {
    switch (task.style()) {
      case FILL:
        graphics.fill(
            task.rect().point().x(),
            task.rect().point().y(),
            task.rect().dimension().width(),
            task.rect().dimension().height(),
            task.color().value());
        break;
      case STROKE:
        graphics.renderOutline(
            task.rect().point().x(),
            task.rect().point().y(),
            task.rect().dimension().width(),
            task.rect().dimension().height(),
            task.color().value());
    }
  }
}
