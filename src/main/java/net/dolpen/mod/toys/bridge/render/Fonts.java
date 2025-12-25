package net.dolpen.mod.toys.bridge.render;

import net.dolpen.mod.toys.model.geometry.Dimension;
import net.minecraft.client.gui.Font;

public class Fonts {

  public static Dimension singleChar(Font font) {
    return new Dimension(font.width("-"), font.lineHeight);
  }
}
