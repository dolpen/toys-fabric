package net.dolpen.mod.toys.feature.hud;

import net.dolpen.mod.toys.bridge.data.Axis;
import net.dolpen.mod.toys.bridge.render.TextRenderer;
import net.dolpen.mod.toys.model.geometry.Point;
import net.dolpen.mod.toys.model.render.Color;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;

public class BoostComponent implements HudComponent {

  /**
   * ロケット花火のブースト持続tick数
   *
   * @see net.minecraft.world.entity.projectile.FireworkRocketEntity
   */
  private static final int RANDOM_PART = 11;

  private static final int STATIC_PART = (1 + 3) * 10;
  private static final int LENGTH = 10;

  private final Minecraft client;
  private int boostTick = 0;

  public BoostComponent(Minecraft client) {
    this.client = client;
  }

  /** ロケット状態 */
  private enum BoostState {
    ACTIVE(Color.RED),
    PARTIAL(Color.YELLOW),
    INACTIVE(Color.WHITE);
    public final Color color;

    BoostState(Color color) {
      this.color = color;
    }
  }

  /**
   * tick 処理
   *
   * @param boostLevel ロケット花火が使用された場合、そのレベル
   */
  private void tickBoost(int boostLevel) {
    this.boostTick = Math.max(0, this.boostTick - 1);
    if (boostLevel == 0) return;
    this.boostTick = RANDOM_PART + 10 * boostLevel;
  }

  /** ロケットブーストの破棄 */
  private void disposeBoost() {
    this.boostTick = 0;
  }

  /** ブースト状態の取得 */
  private BoostState getBoostState() {
    if (this.boostTick <= 0) return BoostState.INACTIVE;
    if (this.boostTick > RANDOM_PART) return BoostState.ACTIVE;
    return BoostState.PARTIAL;
  }

  @Override
  public void accept(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
    LocalPlayer player = client.player;
    if (player == null) {
      return;
    }
    if (!Axis.isFlying(player)) {
      disposeBoost();
      return;
    }
    int boostLevel = Axis.getFireworkRocketLevel(player);
    tickBoost(boostLevel);
    Point offset =
        new Point(
            TextRenderer.offsetUnderScore(client.font, LENGTH),
            (int) (client.font.lineHeight * 1.2));
    TextRenderer.renderRightAlignment(
        guiGraphics,
        offset.join(TextRenderer.center(guiGraphics)),
        String.format("%2d", this.boostTick),
        client.font,
        this.getBoostState().color);
  }
}
