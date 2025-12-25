package net.dolpen.mod.toys.model.geometry;

public record Rect(Point point, Dimension dimension) {
  public Rect offset(Point point) {
    return new Rect(this.point.join(point), this.dimension);
  }
}
