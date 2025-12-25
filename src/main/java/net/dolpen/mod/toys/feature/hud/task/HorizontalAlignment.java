package net.dolpen.mod.toys.feature.hud.task;

import java.util.function.BinaryOperator;

public enum HorizontalAlignment {
  RIGHT((outerWidth, innerWidth) -> outerWidth - innerWidth),
  LEFT((outerWidth, innerWidth) -> 0),
  CENTER((outerWidth, innerWidth) -> (outerWidth - innerWidth) / 2);
  public final BinaryOperator<Integer> op;

  HorizontalAlignment(BinaryOperator<Integer> op) {
    this.op = op;
  }
}
