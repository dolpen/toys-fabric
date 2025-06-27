package net.dolpen.mod.toys.model.render;

/**
 * instead of awt Color
 *
 * @param value
 */
public record Color(int value) {

  public static final Color WHITE = ofRGB(255, 255, 255);
  public static final Color RED = ofRGB(255, 0, 0);
  public static final Color GREEN = ofRGB(0, 255, 0);
  public static final Color BLUE = ofRGB(0, 0, 255);

  public static Color ofRGB(int r, int g, int b) {
    return ofRGBA(r, g, b, 255);
  }

  public static Color ofRGBA(int r, int g, int b, int a) {
    return new Color(((a & 0xFF) << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | (b & 0xFF));
  }
}
