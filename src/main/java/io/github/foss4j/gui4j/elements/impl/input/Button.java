package io.github.foss4j.gui4j.elements.impl.input;

import io.github.foss4j.gui4j.elements.Element;
import io.github.foss4j.gui4j.elements.styling.text.TextAlignment;
import io.github.foss4j.gui4j.elements.styling.text.TextPosition;

public class Button extends Element {
    public Button(String text, float x, float y, float width, float height) {
        super(x, y, width, height);

        setText(text);

        getNormalStyle().setBackgroundColor(0xFF696969);
        getNormalStyle().textStyle.setAlignment(TextAlignment.CENTER);
        getNormalStyle().textStyle.setPosition(TextPosition.MIDDLE);

        getHoveredStyle().setBackgroundColor(0xFF424242);
    }
}
