package turtle;

import java.awt.Color;
import java.io.Serializable;
import java.util.*;
import tools.*;

public class Turtle extends Publisher implements Serializable {
    private Point location;
    private Boolean penUp;
    private List<Point> path;
    private Heading heading;
    private Color color;
    public static Integer WORLD_SIZE = 250;

    public Turtle(Point location, Heading heading) {
        super();
        this.location = location;
        this.heading = heading;
        this.penUp = false;
        this.path = new ArrayList<Point>();
        path.add(location);
        color = Color.RED;
    }
    public Turtle() {
        this(new Point(WORLD_SIZE/2, WORLD_SIZE/2, Color.RED), Heading.NORTH);
    }

    public Boolean getPenUp() {
        return penUp;
    }
    public void setPenUp(Boolean penUp) {
        this.penUp = penUp;

        // this is used for line drawing turtles
        if (penUp && !path.isEmpty()) {
            location.endPoint = true;
        } else {
            path.add(location);
        }
        notifySubscribers();
    }
    public Point getLocation() { return location; }
    public Heading getHeading() { return heading; }
    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }
    public void turn(Heading heading) {
        this.heading = heading;
        notifySubscribers();
    } // used?
    public Iterator<Point> iterator() { return path.iterator(); }
    public void clear() {
        path.clear();
        notifySubscribers();
    }

    /*public void move(Integer steps) {
        switch(heading) {
            case WEST: {
                int xc = location.x - steps;
                if (xc < 0) xc = WORLD_SIZE + xc;
                if (!penUp) {
                    for(int i = 0; i < steps; i++) {
                        int x = location.x - i;
                        if (x < 0) x = WORLD_SIZE + i;
                        path.add(new Point(x, location.y, color));
                    }
                }
                location = new Point(xc, location.y, color);
                notifySubscribers();
                break;
            } case EAST: {
                int xc = (location.x + steps) % WORLD_SIZE;
                if (!penUp) {
                    for(int i = 0; i < steps; i++) {
                        int x = (location.x + i) % WORLD_SIZE;
                        path.add(new Point(x, location.y, color));
                    }
                }
                location = new Point(xc, location.y, color);
                notifySubscribers();
                break;
            } case NORTH: {
                int yc = location.y - steps;
                if (yc < 0) yc = WORLD_SIZE + yc;
                if (!penUp) {
                    for(int i = 0; i < steps; i++) {
                        int y = location.y - i;
                        if (y < 0) y = WORLD_SIZE + i;
                        path.add(new Point(location.x, y, color));
                    }
                }
                location = new Point(location.x, yc, color);
                notifySubscribers();
                break;
            } case SOUTH: {
                int yc = (location.y + steps) % WORLD_SIZE;
                if (!penUp) {
                    for(int i = 0; i < steps; i++) {
                        int y = (location.y + i) % WORLD_SIZE;
                        path.add(new Point(location.x, y, color));
                    }
                }
                location = new Point(location.x, yc, color);
                notifySubscribers();
                break;
            } default: {

            }
        }
    }
*/
    public void move(Integer steps) {
        switch(heading) {
            case WEST: {
                int xc = location.x - steps;
                if (xc < 0) xc = WORLD_SIZE + xc; // xc is negative
                location = new Point(xc, location.y, color, !penUp);
                break;
            } case EAST: {
                int xc = (location.x + steps) % WORLD_SIZE;
                if (xc < 0) xc = WORLD_SIZE + xc; // xc is negative
                location = new Point(xc, location.y, color, !penUp);
                break;
            } case NORTH: {
                int yc = location.y - steps;
                if (yc < 0) yc = WORLD_SIZE + yc; // yc is negative
                location = new Point(location.x, yc, color, !penUp);
                break;
            } case SOUTH: {
                int yc = (location.y + steps) % WORLD_SIZE;
                location = new Point(location.x, yc, color, !penUp);
                break;
            } default: {

            }
        }
        path.add(location);
        notifySubscribers();
    }
/*
    public void move(Integer steps) {
        switch(heading) {
            case WEST: {
                int xc = location.x - steps;
                if (xc < 0) xc = WORLD_SIZE + xc;
//                if (!penUp) {
//                    //System.out.println("filling path");
//                    for(int i = 0; i < steps; i++) {
//                        int x = location.x - i;
//                        if (x < 0) x = WORLD_SIZE + i;
//                        path.add(new Point(x, location.y, color));
//                    }
//                }
                if (!penUp) {
                  //  path.add(location);
                }
                location = new Point(xc, location.y, color);
                if (!penUp) {
                    path.add(location);
                }
                notifySubscribers();
                break;
            } case EAST: {
                int xc = (location.x + steps) % WORLD_SIZE;
                if (!penUp) {
                    for(int i = 0; i < steps; i++) {
                        int x = (location.x + i) % WORLD_SIZE;
                        path.add(new Point(x, location.y, color));
                    }
                }
                location = new Point(xc, location.y, color);
                notifySubscribers();
                break;
            } case NORTH: {
                int yc = location.y - steps;
                if (yc < 0) yc = WORLD_SIZE + yc;
                if (!penUp) {
                    for(int i = 0; i < steps; i++) {
                        int y = location.y - i;
                        if (y < 0) y = WORLD_SIZE + i;
                        path.add(new Point(location.x, y, color));
                    }
                }
                location = new Point(location.x, yc, color);
                //System.out.println("notifying");
                notifySubscribers();
                break;
            } case SOUTH: {
                int yc = (location.y + steps) % WORLD_SIZE;
                if (!penUp) {
                    for(int i = 0; i < steps; i++) {
                        int y = (location.y + i) % WORLD_SIZE;
                        path.add(new Point(location.x, y, color));
                    }
                }
                location = new Point(location.x, yc, color);
                notifySubscribers();
                break;
            } default: {

            }
        }
    }
*/


}
