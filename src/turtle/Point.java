package turtle;

import java.awt.*;
import java.io.Serializable;

class Point implements Serializable {
    int x, y;
    Color color;
    boolean endPoint;

    public Point(int x, int y, Color color, boolean end) {
        this.x = x;
        this.y = y;
        this.color = color;
        endPoint = end;
    }

    public Point(int x, int y, Color color) {
        this(x, y, color, false);
    }
}
