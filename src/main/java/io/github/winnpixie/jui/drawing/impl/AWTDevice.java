package io.github.winnpixie.jui.drawing.impl;

import io.github.winnpixie.jui.drawing.GraphicsDevice;
import io.github.winnpixie.jui.drawing.TextRenderer;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class AWTDevice implements GraphicsDevice, TextRenderer {
    private final Graphics2D graphics;

    public AWTDevice(Graphics2D graphics) {
        this.graphics = graphics;
    }

    @Override
    public void fillRect(float x, float y, float width, float height, int rgbaColor) {
        graphics.setColor(new Color(rgbaColor, true));
        graphics.fill(new Rectangle2D.Float(x, y, width, height));
    }

    @Override
    public void translate(float x, float y) {
        graphics.translate(x, y);
    }

    @Override
    public void scale(float x, float y) {
        graphics.scale(x, y);
    }

    @Override
    public void rotate(float angle, float x, float y) {
        graphics.rotate(angle, x, y);
    }

    @Override
    public float getFontHeight() {
        return graphics.getFontMetrics().getHeight();
    }

    @Override
    public float getStringWidth(String text) {
        return (float) graphics.getFontMetrics().getStringBounds(text, graphics).getWidth();
    }

    @Override
    public void drawString(String text, float x, float y, int rgbaColor) {
        graphics.setColor(new Color(rgbaColor, true));
    }
}
