package net.dolpen.mod.toys.bridge.data;

import net.minecraft.client.player.LocalPlayer;

public class Axis {

  public static String getSpeed(LocalPlayer player) {
    double moveDistance = player.getDeltaMovement().horizontalDistance();
    return String.format("%3.2f m/s", moveDistance / 0.05f);
  }

  public static boolean isFlying(LocalPlayer player) {
    return player.isFallFlying();
  }

  // 水平360方位
  public static int getHorizontalDirection(LocalPlayer player) {
    return (int) (player.getYHeadRot() + 0.5);
  }

  // 垂直360方位
  public static int getPitch(LocalPlayer player) {
    return (int) -(player.getXRot() + 0.5);
  }
}
