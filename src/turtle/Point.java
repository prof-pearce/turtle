package turtle;

import java.awt.*;
import java.io.Serializable;

class Point implements Serializable {
    int x, y;
    Color color;
    boolean endPoint;

    public Point(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        endPoint = false;
    }
}
