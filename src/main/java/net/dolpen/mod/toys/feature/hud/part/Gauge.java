package net.dolpen.mod.toys.feature.hud.part;

public class Gauge {

  private final int maxValue;
  private final int minValue;

  public Gauge(int maxValue, int minValue) {
    this.maxValue = Math.max(maxValue, minValue);
    this.minValue = Math.min(maxValue, minValue);
  }

  public int getPosition(int value, int fieldLength) {
    int clamped = Math.clamp(value, minValue, maxValue);
    return fieldLength * clamped / maxValue;
  }
}
