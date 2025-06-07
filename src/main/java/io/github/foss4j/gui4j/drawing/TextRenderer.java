package io.github.foss4j.gui4j.drawing;

public interface TextRenderer {
    float getFontHeight();

    float getStringWidth(String text);

    void drawString(String text, float x, float y, int color);
}
