package net.dolpen.mod.toys.feature.hud.task;

import net.dolpen.mod.toys.model.geometry.Rect;
import net.dolpen.mod.toys.model.render.Color;

public record RectTask(Rect rect, Color color, ShapeStyle style) {}
