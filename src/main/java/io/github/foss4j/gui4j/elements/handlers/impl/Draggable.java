package io.github.foss4j.gui4j.elements.handlers.impl;

import io.github.foss4j.gui4j.elements.Element;
import io.github.foss4j.gui4j.elements.handlers.ElementEventListener;

import java.awt.*;

public class Draggable extends ElementEventListener {
    private boolean dragging;

    public float initialX;
    public float initialY;

    @Override
    public void onDraw(int mouseX, int mouseY, float partialTicks) {
        if (!dragging) return;

        float newX = mouseX + initialX;
        float newY = mouseY + initialY;

        float minX = 1;
        float minY = 1;

        // TODO: Find a way to make this a bit more... generic?
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        float maxX = screenSize.width - 1;
        float maxY = screenSize.height - 1;

        Element parent = getSource().getParent();
        if (parent != null) {
            minX = parent.getX() + 1;
            minY = parent.getY() + 1;

            maxX = parent.getX() + parent.getWidth() - 1;
            maxY = parent.getY() + parent.getHeight() - 1;
        }

        float deltaX = newX - getSource().getX();
        float deltaY = newY - getSource().getY();

        float[] bounds = getSource().getBounds();
        if (bounds[0] + deltaX < minX) {
            newX = minX;
        }

        if (bounds[1] + deltaY < minY) {
            newY = minY;
        }

        if (bounds[2] + deltaX > maxX) {
            newX = maxX - (bounds[2] - bounds[0]);
        }

        if (bounds[3] + deltaY > maxY) {
            newY = maxY - (bounds[3] - bounds[1]);
        }

        getSource().setX(newX);
        getSource().setY(newY);
    }

    @Override
    public void onMouseDown(int mouseX, int mouseY, int button) {
        if (button != 0) return;

        initialX = getSource().getX() - mouseX;
        initialY = getSource().getY() - mouseY;
        dragging = true;
    }

    @Override
    public void onMouseUp(int mouseX, int mouseY, int button) {
        if (button == 0) dragging = false;
    }
}
