package io.github.winnpixie.jui.drawing;

public interface GraphicsDevice {
    void fillRect(float x, float y, float width, float height, int rgbaColor);

    void translate(float x, float y);

    void scale(float x, float y);

    void rotate(float angle, float x, float y);
}
