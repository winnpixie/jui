package io.github.foss4j.gui4j.elements.handlers.impl;

import io.github.foss4j.gui4j.elements.Element;
import io.github.foss4j.gui4j.elements.handlers.ElementEventListener;

public class Collapsible extends ElementEventListener {
    private final Element container;

    public Collapsible(Element container) {
        this.container = container;
    }

    @Override
    public void onMouseDown(int mouseX, int mouseY, int button) {
        if (button == 1) container.getNormalStyle().setVisible(!container.getNormalStyle().isVisible());
    }
}
