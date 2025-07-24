package io.github.winnpixie.jui.elements.impl;

import io.github.winnpixie.jui.elements.Element;

public class Label extends Element {
    public Label(String text, float x, float y, float width, float height) {
        super(x, y, width, height);

        setText(text);
    }
}
