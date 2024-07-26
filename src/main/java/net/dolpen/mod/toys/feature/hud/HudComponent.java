package net.dolpen.mod.toys.feature.hud;

import java.util.function.BiConsumer;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;

// HudRenderCallback.EVENT.register
@FunctionalInterface
public interface HudComponent extends BiConsumer<GuiGraphics, DeltaTracker> {}
