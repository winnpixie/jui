package io.github.foss4j.gui4j.elements.impl.input;

import io.github.foss4j.gui4j.elements.Element;
import io.github.foss4j.gui4j.elements.handlers.ElementEventListener;

import java.awt.event.KeyEvent;

public class TextArea extends Element {
    public TextArea(String initialText, float x, float y, float width, float height) {
        super(x, y, width, height);

        setText(initialText);

        addListener(new ElementEventListener() {
            @Override
            public void onKeyPressed(char charCode, int keyCode) {
                String oldText = getText();

                if (keyCode == KeyEvent.VK_BACK_SPACE || keyCode == KeyEvent.VK_DELETE) {
                    if (!oldText.isEmpty()) setText(oldText.substring(0, oldText.length() - 1));
                } else if (isAllowedCharacter(charCode)) {
                    setText(oldText + charCode);
                }

                if (!oldText.equals(getText())) onValueChanged(oldText, getText());
            }
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
    private boolean isAllowedCharacter(char chr) {
        return true;
    }
}
