package io.github.foss4j.gui4j.drawing;

public interface GraphicsDevice {
    void drawFilledRect(float x, float y, float width, float height, int color);

    void translate(float x, float y);

    void scale(float x, float y);
}
