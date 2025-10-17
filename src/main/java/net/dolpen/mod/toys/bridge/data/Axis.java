package net.dolpen.mod.toys.bridge.data;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.FireworkRocketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Fireworks;

public class Axis {

  /**
   * 水平移動速度の取得
   *
   * @param player クライアントで操作中のプレイヤー情報
   * @param deltaTracker ゲームループの差分管理オブジェクト
   * @return 水平移動速度[block/sec]
   */
  public static double getSpeed(LocalPlayer player, DeltaTracker deltaTracker) {
    double moveDistance = player.getDeltaMovement().horizontalDistance();
    return moveDistance / deltaTracker.getRealtimeDeltaTicks();
  }

  /**
   * エリトラ飛行中かつかどうかの判定
   *
   * @param player クライアントで操作中のプレイヤー情報
   * @return このティックに飛行中ならtrue
   */
  public static boolean isFlying(LocalPlayer player) {
    return player.isFallFlying();
  }

  /**
   * エリトラ飛行中にロケット花火が使用されたかどうかの判定
   *
   * @param player クライアントで操作中のプレイヤー情報
   * @return このティックに使用されてなければ0、使用された場合はその飛行距離データ
   */
  public static int getFireworkRocketLevel(LocalPlayer player) {
    // 飛行してないのに花火使ってもただ打ち上げるだけなので無効
    if (!isFlying(player)) return 0;
    // このティックにアイテム使用中であること
    if (!player.isUsingItem()) return 0;
    // 使用したアクティブスロットまたはオフハンドスロットのスタック情報とその内容を判定
    ItemStack stack = player.getUseItem();
    if (stack.getItem() instanceof FireworkRocketItem && stack.has(DataComponents.FIREWORKS)) {
      // 花火である場合は、花火のデータを取得して飛行距離の返却を試みる
      Fireworks fireworksData = stack.get(DataComponents.FIREWORKS);
      return fireworksData != null ? fireworksData.flightDuration() : 0;
    }
    return 0;
  }

  /**
   * 操作しているプレイヤーの視線方向から、水平360方位を抽出する （飛行中であれば移動の方向とはほぼ一致するが、慣性でズレるため完全一致ではない）
   *
   * @param player クライアントで操作中のプレイヤー情報
   * @return 水平360方位
   */
  public static int getHorizontalDirection(LocalPlayer player) {
    return (int) (player.getYHeadRot() + 0.5);
  }

  /**
   * 操作しているプレイヤーの視線方向から、垂直360方位を抽出する （飛行中であれば移動の方向とはほぼ一致するが、慣性や重力でズレるため完全一致ではない）
   *
   * @param player クライアントで操作中のプレイヤー情報
   * @return 垂直360方位
   */
  public static int getPitch(LocalPlayer player) {
    return (int) -(player.getXRot() + 0.5);
  }
}
