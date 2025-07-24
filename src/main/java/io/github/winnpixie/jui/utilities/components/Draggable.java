package io.github.winnpixie.jui.utilities.components;

import io.github.winnpixie.jui.elements.Element;
import io.github.winnpixie.jui.events.listeners.DrawListener;
import io.github.winnpixie.jui.events.listeners.MouseListener;
import io.github.winnpixie.jui.events.mouse.MouseAction;
import io.github.winnpixie.jui.events.mouse.MouseButton;
import io.github.winnpixie.jui.events.mouse.MouseEvent;
import io.github.winnpixie.jui.utilities.Cursor;

import java.awt.*;

public class Draggable implements DrawListener, MouseListener {
    private boolean dragging;

    private float initialX;
    private float initialY;

    @Override
    public void onDrawEvent(Element source, float deltaTime) {
        if (!dragging) return;

        float newX = Cursor.getX() + initialX;
        float newY = Cursor.getY() + initialY;

        float minX = 1;
        float minY = 1;

        // TODO: Find a way to make this a bit more... generic?
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        float maxX = screenSize.width - 1f;
        float maxY = screenSize.height - 1f;

        Element parent = source.getParent();
        if (parent != null) {
            minX = parent.getX() + 1;
            minY = parent.getY() + 1;

            maxX = parent.getX() + parent.getWidth() - 1;
            maxY = parent.getY() + parent.getHeight() - 1;
        }

        float deltaX = newX - source.getX();
        float deltaY = newY - source.getY();

        float[] bounds = source.getBounds();
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

        source.setX(newX);
        source.setY(newY);
    }

    @Override
    public void onMouseEvent(Element source, MouseEvent event) {
        if (event.getButton() != MouseButton.LEFT) return;

        if (event.getAction() == MouseAction.CLICK) {
            initialX = source.getX() - event.getX();
            initialY = source.getY() - event.getY();
            dragging = true;
        } else {
            dragging = false;
        }
    }
}
