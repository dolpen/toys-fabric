package net.dolpen.mod.toys.model.geometry;

import java.util.List;

public record Rect(Point point, Dimension dimension) {
  public List<Line> toEdgeLine() {
    Point tr = point.withX(dimension.width());
    Point bl = point.withY(dimension.height());
    Point br = bl.withX(dimension.width());
    return List.of(new Line(point, tr), new Line(point, bl), new Line(tr, br), new Line(bl, br));
  }

  public Rect offset(Point point) {
    return new Rect(this.point.join(point), this.dimension);
  }
}
