package io.github.winnpixie.jui.elements;

import io.github.winnpixie.jui.events.key.KeyboardAction;
import io.github.winnpixie.jui.events.key.KeyboardEvent;
import io.github.winnpixie.jui.events.listeners.KeyboardListener;
import io.github.winnpixie.jui.events.listeners.MouseListener;
import io.github.winnpixie.jui.events.listeners.UpdateListener;
import io.github.winnpixie.jui.events.mouse.MouseAction;
import io.github.winnpixie.jui.events.mouse.MouseEvent;

public class Controller {
    private final Element element;

    public Controller(Element element) {
        this.element = element;
    }

    public void update() {
        if (!element.getNormalStyle().isVisible()) return;

        element.onUpdate();

        for (UpdateListener updateListener : element.getUpdateListeners()) {
            updateListener.onUpdate(element);
        }

        for (Element child : element.getChildren()) {
            child.update();
        }
    }

    public void clickMouse(int mouseX, int mouseY, int button) {
        if (!element.getNormalStyle().isVisible()) return;

        if (element.isInBounds(mouseX, mouseY)) {
            element.setFocused(true);

            for (MouseListener mouseListener : element.getMouseListeners()) {
                mouseListener.onMouseEvent(element, new MouseEvent(mouseX, mouseY, button, MouseAction.CLICK));
            }
        } else {
            element.setFocused(false);
        }

        for (Element child : element.getChildren()) {
            child.clickMouse(mouseX, mouseY, button);
        }
    }

    public void releaseMouse(int mouseX, int mouseY, int button) {
        if (!element.getNormalStyle().isVisible()) return;

        for (MouseListener mouseListener : element.getMouseListeners()) {
            mouseListener.onMouseEvent(element, new MouseEvent(mouseX, mouseY, button, MouseAction.RELEASE));
        }

        for (Element child : element.getChildren()) {
            child.releaseMouse(mouseX, mouseY, button);
        }
    }

    public void pressKey(int keyCode, char keyChar) {
        if (!element.getNormalStyle().isVisible()) return;

        if (element.isFocused()) {
            for (KeyboardListener keyListener : element.getKeyboardListeners()) {
                keyListener.onKeyEvent(element, new KeyboardEvent(keyCode, keyChar, KeyboardAction.PRESS));
            }
        }

        for (Element child : element.getChildren()) {
            child.pressKey(keyCode, keyChar);
        }
    }

    public void releaseKey(int keyCode, char keyChar) {
        if (!element.getNormalStyle().isVisible()) return;

        if (element.isFocused()) {
            for (KeyboardListener keyListener : element.getKeyboardListeners()) {
                keyListener.onKeyEvent(element, new KeyboardEvent(keyCode, keyChar, KeyboardAction.RELEASE));
            }
        }

        for (Element child : element.getChildren()) {
            child.releaseKey(keyCode, keyChar);
        }
    }
}
