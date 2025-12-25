package net.dolpen.mod.toys.feature.hud.task;

import java.util.function.BinaryOperator;

public enum VerticalAlignment {
  TOP((outerHeight, innerHeight) -> outerHeight - innerHeight),
  BOTTOM((outerHeight, innerHeight) -> 0),
  CENTER((outerHeight, innerHeight) -> (outerHeight - innerHeight) / 2);
  final BinaryOperator<Integer> op;

  VerticalAlignment(BinaryOperator<Integer> op) {
    this.op = op;
  }
}
