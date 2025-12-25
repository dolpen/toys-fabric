package net.dolpen.mod.toys.feature.hud.task;

import net.dolpen.mod.toys.model.geometry.Rect;
import net.dolpen.mod.toys.model.render.Color;
import net.minecraft.client.gui.Font;

public record StringTask(
    String content,
    Font font,
    Color color,
    Rect box,
    HorizontalAlignment horizontalAlignment,
    VerticalAlignment verticalAlignment) {}
