package io.github.winnpixie.jui.events.listeners;

import io.github.winnpixie.jui.elements.Element;
import io.github.winnpixie.jui.events.mouse.MouseEvent;

public interface MouseListener {
    void onMouseEvent(Element source, MouseEvent event);
}
