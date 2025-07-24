package io.github.winnpixie.jui.elements;

import io.github.winnpixie.jui.elements.styling.BorderStyle;
import io.github.winnpixie.jui.elements.styling.ElementStyle;
import io.github.winnpixie.jui.elements.styling.text.TextAlignment;
import io.github.winnpixie.jui.elements.styling.text.TextPosition;
import io.github.winnpixie.jui.elements.styling.text.TextStyle;
import io.github.winnpixie.jui.events.listeners.DrawListener;
import io.github.winnpixie.jui.utilities.Cursor;

public class Renderer {
    private final Element element;

    public Renderer(Element element) {
        this.element = element;
    }

    public void draw(float deltaTime) {
        if (!element.getNormalStyle().isVisible()) return;

        for (DrawListener drawListener : element.getDrawListeners()) {
            drawListener.onDrawEvent(element, deltaTime);
        }

        element.setHovered(element.isInBounds(Cursor.getX(), Cursor.getY()));
        ElementStyle activeStyle = element.isHovered()
                ? element.getHoveredStyle() : element.getNormalStyle();

        drawBackground(activeStyle);
        drawBorders(activeStyle.borderStyle);
        drawText(activeStyle.textStyle);

        element.onDraw(deltaTime);

        for (Element child : element.getChildren()) child.draw(deltaTime);
    }

    private void drawBackground(ElementStyle style) {
        if (!style.isShowBackground()) return;

        float eX = element.getX();
        float eY = element.getY();
        float eWidth = element.getWidth();
        float eHeight = element.getHeight();

        element.getGraphicsDevice().fillRect(eX, eY, eWidth, eHeight, style.getBackgroundColor());
    }

    private void drawBorders(BorderStyle style) {
        float eX = element.getX();
        float eY = element.getY();
        float eWidth = element.getWidth();
        float eHeight = element.getHeight();

        float borderTop = style.getTop();
        float borderBottom = style.getBottom();
        float borderLeft = style.getLeft();
        float borderRight = style.getRight();

        if (borderTop > 0) {
            element.getGraphicsDevice().fillRect(eX,
                    eY - borderTop,
                    eWidth + borderRight,
                    borderTop,
                    style.getColorTop());
        }

        if (borderBottom > 0) {
            element.getGraphicsDevice().fillRect(eX - borderLeft,
                    eY + eHeight,
                    eWidth + borderLeft,
                    borderTop,
                    style.getColorBottom());
        }

        if (borderLeft > 0) {
            element.getGraphicsDevice().fillRect(eX - borderLeft,
                    eY - borderTop,
                    borderLeft,
                    eHeight + borderTop,
                    style.getColorLeft());
        }

        if (borderRight > 0) {
            element.getGraphicsDevice().fillRect(eX + eWidth,
                    eY,
                    borderRight,
                    eHeight + borderBottom,
                    style.getColorBottom());
        }
    }

    private void drawText(TextStyle style) {
        String text = element.getText();
        if (text == null) return;
        if (text.isEmpty()) return;

        if (!style.isVisible()) return;

        float eX = element.getX();
        float eY = element.getY();
        float eWidth = element.getWidth();
        float eHeight = element.getHeight();

        TextAlignment alignment = style.getAlignment();
        TextPosition position = style.getPosition();

        // TODO: De-Minecraft this, LOL
        if (style.isItalic()) text = "\247o" + text;
        if (style.isBold()) text = "\247l" + text;

        float textWidth = 0;
        if (alignment != TextAlignment.LEFT) {
            textWidth = element.getTextRenderer().getStringWidth(text);
        }

        float xOffset = style.getOffsetX();
        if (alignment == TextAlignment.CENTER) {
            xOffset += (eWidth / 2f) - (textWidth / 2f);
        } else if (alignment == TextAlignment.RIGHT) {
            xOffset = eWidth - textWidth - xOffset;
        }

        float fontHeight = element.getTextRenderer().getFontHeight();
        float yOffset = style.getOffsetY();
        if (position == TextPosition.MIDDLE) {
            yOffset += (eHeight / 2f) - (fontHeight / 2f);
        } else if (position == TextPosition.BOTTOM) {
            yOffset = eHeight - fontHeight - yOffset;
        }

        element.getGraphicsDevice().translate(eX + xOffset, eY + yOffset);
        element.getGraphicsDevice().scale(style.getScaleX(), style.getScaleY());
        element.getGraphicsDevice().translate(-(eX + xOffset), -(eY + yOffset));

        // TODO: Line-wrap
        // TODO: Text shadow

        element.getTextRenderer().drawString(text,
                eX + xOffset,
                eY + yOffset,
                style.getColor());
    }
}