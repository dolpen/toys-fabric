package net.dolpen.mod.toys.model.geometry;

public record Point(int x, int y) {
  public Point join(Point other) {
    return new Point(x + other.x(), y + other.y());
  }

  public Point with(int offsetX, int offsetY) {
    return new Point(x + offsetX, y + offsetY);
  }

  public Point withX(int offsetX) {
    return new Point(x + offsetX, y);
  }

  public Point withY(int offsetY) {
    return new Point(x, y + offsetY);
  }
}
