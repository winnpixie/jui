package io.github.winnpixie.jui.elements.impl.input;

import io.github.winnpixie.jui.elements.Element;

import java.awt.event.KeyEvent;

public class TextArea extends Element {
    public TextArea(String initialText, float x, float y, float width, float height) {
        super(x, y, width, height);

        setText(initialText);

        getKeyboardListeners().add((source, event) -> {
            String oldText = getText();

            if (event.getKey() == KeyEvent.VK_BACK_SPACE || event.getKey() == KeyEvent.VK_DELETE) {
                if (!oldText.isEmpty()) setText(oldText.substring(0, oldText.length() - 1));
            } else if (isAllowedCharacter(event.getCharacter())) {
                setText(oldText + event.getCharacter());
            }

            if (!oldText.equals(getText())) onValueChanged(oldText, getText());
        });

        getNormalStyle().textStyle.setLineWrap(true);
    }

    public void onValueChanged(String oldValue, String newValue) {
    }

    @Override
    public void setText(String text) {
        if (text == null) text = "";

        super.setText(text);
    }

    // TODO: Filter
    private boolean isAllowedCharacter(char character) {
        return true;
    }
}
