package io.github.winnpixie.jui.utilities.components;

import io.github.winnpixie.jui.elements.Element;
import io.github.winnpixie.jui.events.listeners.MouseListener;
import io.github.winnpixie.jui.events.mouse.MouseAction;
import io.github.winnpixie.jui.events.mouse.MouseButton;
import io.github.winnpixie.jui.events.mouse.MouseEvent;

public class Collapsible implements MouseListener {
    private final Element container;

    public Collapsible(Element container) {
        this.container = container;
    }

    @Override
    public void onMouseEvent(Element source, MouseEvent event) {
        if (event.getAction() != MouseAction.CLICK
                || event.getButton() != MouseButton.RIGHT) return;

        container.getNormalStyle().setVisible(!container.getNormalStyle().isVisible());
    }
}
