package io.github.winnpixie.jui.events.listeners;

import io.github.winnpixie.jui.elements.Element;
import io.github.winnpixie.jui.events.key.KeyboardEvent;

public interface KeyboardListener {
    void onKeyEvent(Element source, KeyboardEvent event);
}
