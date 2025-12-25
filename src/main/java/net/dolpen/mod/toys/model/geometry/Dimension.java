package net.dolpen.mod.toys.model.geometry;

public record Dimension(int width, int height) {
  public Dimension mulW(int multiplier) {
    return new Dimension(width * multiplier, height);
  }

  public Dimension mulH(int multiplier) {
    return new Dimension(width, height * multiplier);
  }
}
