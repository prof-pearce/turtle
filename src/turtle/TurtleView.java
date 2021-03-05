package turtle;

import tools.Subscriber;

import javax.swing.*;
import javax.swing.border.Border;
import java.util.*;
import java.awt.*;

public class TurtleView extends JPanel implements Subscriber {

    private Turtle turtle;

    public TurtleView(Turtle turtle) {
        this.turtle = turtle;
        turtle.subscribe(this);
        setSize(Turtle.WORLD_SIZE, Turtle.WORLD_SIZE);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);

    }

    public void setTurtle(Turtle t) {
        turtle.unSubscribe(this);
        turtle = t;
        t.subscribe(this);
        repaint();
    }

    public void update() {
        repaint();
    }

    /*public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Color oldColor = gc.getColor();
        Iterator<turtle.Point> it = turtle.iterator();
        while(it.hasNext()) {
            turtle.Point point = it.next();
            gc.setColor(point.color);
            gc.fillOval(point.x,  point.y, 5, 5);
        }
        gc.setColor(Color.BLACK);
        if (turtle.getPenUp()) {
            gc.drawOval(turtle.getLocation().x,turtle.getLocation().y , 7, 7);
        } else {
            gc.fillOval(turtle.getLocation().x,turtle.getLocation().y , 7, 7);
        }
        gc.setColor(oldColor);
    }*/
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Color oldColor = gc.getColor();
        Iterator<turtle.Point> it = turtle.iterator();
        //Point last = null;
        //if (it.hasNext()) last = it.next();
        while(it.hasNext()) {

            Point point = it.next();
            /*
            if (!last.endPoint) {
                gc.drawLine(last.x, last.y, nextPoint.x, nextPoint.y);
            }
            last = nextPoint;
           */
            gc.setColor(point.color);

            gc.fillOval(point.x,  point.y, 5, 5);
        }
        gc.setColor(Color.BLACK);
        if (turtle.getPenUp()) {
            gc.drawOval(turtle.getLocation().x,turtle.getLocation().y , 7, 7);
        } else {
            gc.fillOval(turtle.getLocation().x,turtle.getLocation().y , 7, 7);
        }
        gc.setColor(oldColor);
    }

}
