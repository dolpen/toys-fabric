package net.dolpen.mod.toys.feature.hud;

import java.util.function.BiConsumer;
import net.dolpen.mod.toys.core.stereotype.Component;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;

@FunctionalInterface
interface HudComponent extends BiConsumer<GuiGraphicsExtractor, DeltaTracker>, Component {}
