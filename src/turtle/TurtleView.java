package turtle;

import tools.Subscriber;

import javax.swing.*;
import javax.swing.border.Border;
import java.util.*;
import java.awt.*;

public class TurtleView extends JPanel implements Subscriber {

    private Turtle myTurtle;

    public TurtleView(Turtle turtle) {
        this.myTurtle = turtle;
        turtle.subscribe(this);
        setSize(Turtle.WORLD_SIZE, Turtle.WORLD_SIZE);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);

    }

    public void setMyTurtle(Turtle t) {
        myTurtle.unSubscribe(this);
        myTurtle = t;
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

        Iterator<turtle.Point> it = myTurtle.iterator();
        turtle.Point last = null;
        if (it.hasNext()) last = it.next();
        while(it.hasNext()) {

            //Point point = it.next();
            turtle.Point nextPoint = it.next();

            if (!last.endPoint) {
                gc.setColor(nextPoint.color);
                gc.drawLine(last.x, last.y, nextPoint.x, nextPoint.y);
            }
            last = nextPoint;

            //gc.setColor(point.color);

           // gc.fillOval(point.x,  point.y, 5, 5);
        }
        gc.setColor(Color.BLACK);
        //TurtleShape ts = new TurtleShape(turtle.getLocation().x,turtle.getLocation().y, 40, turtle.getHeading());
        TurtleShape ts = new TurtleShape(myTurtle);
        ts.draw((Graphics2D)gc);
        /*if (turtle.getPenUp()) {
            TurtleShape ts = new TurtleShape(turtle.getLocation().x,turtle.getLocation().y, 40, turtle.getHeading());
            ts.draw((Graphics2D)gc);
            //gc.drawOval(turtle.getLocation().x,turtle.getLocation().y , 7, 7);
        } else {
            TurtleShape ts = new TurtleShape(turtle.getLocation().x,turtle.getLocation().y, 40, turtle.getHeading());
            ts.draw((Graphics2D)gc);
            //gc.fillOval(turtle.getLocation().x,turtle.getLocation().y , 7, 7);
        }*/
        gc.setColor(oldColor);
    }

}
