package io.github.foss4j.gui4j.elements.impl;

import io.github.foss4j.gui4j.elements.Element;

public class Label extends Element {
    public Label(String text, float x, float y, float width, float height) {
        super(x, y, width, height);

        setText(text);
    }
}
