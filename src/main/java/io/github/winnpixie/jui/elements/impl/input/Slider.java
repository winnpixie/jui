package io.github.winnpixie.jui.elements.impl.input;

import io.github.winnpixie.jui.elements.Element;
import io.github.winnpixie.jui.elements.styling.ElementStyle;
import io.github.winnpixie.jui.elements.styling.text.TextAlignment;
import io.github.winnpixie.jui.events.mouse.MouseAction;
import io.github.winnpixie.jui.events.mouse.MouseButton;
import io.github.winnpixie.jui.utilities.Cursor;

public class Slider extends Element {
    private double value;
    private final double minimum;
    private final double maximum;

    private boolean dragging;

    public Slider(float x, float y, float width, float height, double value) {
        this(x, y, width, height, value, 0.0, 1.0);
    }

    public Slider(float x, float y, float width, float height, double initialValue, double minimum, double maximum) {
        super(x, y, width, height);

        this.value = initialValue;
        this.minimum = minimum;
        this.maximum = maximum;

        getMouseListeners().add(((source, event) -> {
            if (event.getButton() != MouseButton.LEFT) return;

            dragging = event.getAction() == MouseAction.CLICK;
        }));

        getNormalStyle().textStyle.setAlignment(TextAlignment.CENTER);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void onValueChanged(double oldValue, double newValue) {
    }

    @Override
    public void onDraw(float deltaTime) {
        if (dragging) {
            double oldValue = value;
            float relativeMouseX = Cursor.getX() - getX();

            value = (minimum + Math.min(Math.max(relativeMouseX / getWidth(), 0f), 1f) * (maximum - minimum));
            onValueChanged(oldValue, value);
        }

        float lineWidth = 1f;
        float lineSize = 8f;
        float halfSize = lineWidth / 2f;

        float normalized = (float) ((value - minimum) / (maximum - minimum));
        float xOffset = normalized * getWidth();
        if (xOffset + lineWidth > getWidth()) xOffset = getWidth() - lineWidth;

        ElementStyle style = isHovered() ? getNormalStyle() : getHoveredStyle();

        // Vertical bar
        getGraphicsDevice().fillRect(getX() + xOffset,
                getY() + (getHeight() / 2f) - (lineSize / 2f),
                lineWidth,
                lineSize,
                style.getForegroundColor());

        // Horizontal Line
        getGraphicsDevice().fillRect(getX() + 1,
                getY() + (getHeight() / 2f) - halfSize,
                getWidth() - 2,
                lineWidth,
                style.getForegroundColor());
    }
}
