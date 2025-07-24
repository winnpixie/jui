package io.github.winnpixie.jui.drawing;

public interface TextRenderer {
    float getFontHeight();

    float getStringWidth(String text);

    void drawString(String text, float x, float y, int rgbaColor);
}
