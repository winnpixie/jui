package io.github.winnpixie.jui.utilities;

public final class Cursor {
    private static int x = -1;
    private static int y = -1;

    private Cursor() {
    }

    public static int getX() {
        return x;
    }

    public static void setX(int x) {
        Cursor.x = x;
    }

    public static int getY() {
        return y;
    }

    public static void setY(int y) {
        Cursor.y = y;
    }
}
