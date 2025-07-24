package io.github.winnpixie.jui.events.mouse;

public enum MouseButton {
    LEFT(0),
    RIGHT(1),
    MIDDLE(2),

    UNKNOWN(-1);

    private final int id;

    MouseButton(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static MouseButton from(int raw) {
        for (MouseButton mouseButton : values()) {
            if (mouseButton.id == raw) return mouseButton;
        }

        return UNKNOWN;
    }
}
