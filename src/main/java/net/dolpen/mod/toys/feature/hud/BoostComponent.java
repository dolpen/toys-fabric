package net.dolpen.mod.toys.feature.hud;

import net.dolpen.mod.toys.bridge.data.Axis;
import net.dolpen.mod.toys.bridge.render.Fonts;
import net.dolpen.mod.toys.bridge.render.Renderer;
import net.dolpen.mod.toys.feature.hud.part.Gauge;
import net.dolpen.mod.toys.feature.hud.task.RectTask;
import net.dolpen.mod.toys.feature.hud.task.ShapeStyle;
import net.dolpen.mod.toys.model.geometry.Dimension;
import net.dolpen.mod.toys.model.geometry.Point;
import net.dolpen.mod.toys.model.geometry.Rect;
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

  private final Minecraft client;
  private final Gauge gauge = new Gauge(0, RANDOM_PART + 10 * 3);
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
    Renderer renderer = new Renderer(guiGraphics);
    Dimension fontMetrics = Fonts.singleChar(client.font);
    Point topLeft = renderer.center().with(fontMetrics.width(), fontMetrics.height() + 2);
    Rect frame = new Rect(topLeft, fontMetrics.mulW(10));
    Rect inner =
        new Rect(
            topLeft,
            new Dimension(
                gauge.getPosition(this.boostTick, frame.dimension().width()),
                frame.dimension().height()));
    renderer.accept(new RectTask(inner, this.getBoostState().color, ShapeStyle.FILL));
    renderer.accept(new RectTask(frame, Color.WHITE, ShapeStyle.STROKE));
  }
}
