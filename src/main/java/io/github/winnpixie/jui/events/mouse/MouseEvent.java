package io.github.winnpixie.jui.events.mouse;

public class MouseEvent {
    private final int x;
    private final int y;
    private final int rawButton;
    private final MouseButton button;
    private final MouseAction action;

    public MouseEvent(int x, int y, int button, MouseAction action) {
        this.x = x;
        this.y = y;
        this.rawButton = button;
        this.button = MouseButton.from(button);
        this.action = action;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRawButton() {
        return rawButton;
    }

    public MouseButton getButton() {
        return button;
    }

    public MouseAction getAction() {
        return action;
    }
}
