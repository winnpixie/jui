package io.github.winnpixie.jui.events.key;

public class KeyboardEvent {
    private final int key;
    private final char character;
    private final KeyboardAction action;

    public KeyboardEvent(int key, char character, KeyboardAction action) {
        this.key = key;
        this.character = character;
        this.action = action;
    }

    public int getKey() {
        return key;
    }

    public char getCharacter() {
        return character;
    }

    public KeyboardAction getAction() {
        return action;
    }
}
