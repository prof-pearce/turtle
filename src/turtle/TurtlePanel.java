package turtle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import tools.*;

public class TurtlePanel extends JPanel implements ActionListener {
    TurtleView view;
    Turtle turtle;
    //JTextField northField, eastField, westField, southField;

    //JCheckBox penButton;
    //JFrame frame;

    public TurtlePanel() {
        turtle = new Turtle();
        TurtleView view = new TurtleView(turtle);
        view.setBackground((Color.WHITE));
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground((Color.cyan));
        JButton north, south, east, west, clear, pen, color;
        JPanel p = new JPanel();
        north = new JButton("North");
        north.addActionListener(this);
        p.add(north);
        controlPanel.add(p);

        p = new JPanel();
        east = new JButton("East");
        east.addActionListener(this);
        p.add(east);
        controlPanel.add(p);

        p = new JPanel();
        west = new JButton("West");
        west.addActionListener(this);
        p.add(west);
        controlPanel.add(p);

        p = new JPanel();
        south = new JButton("South");
        south.addActionListener(this);
        p.add(south);
        controlPanel.add(p);

        p = new JPanel();
        clear = new JButton("Clear");
        clear.addActionListener(this);
        p.add(clear);
        controlPanel.add(p);

        p = new JPanel();
        pen = new JButton("Pen");
        pen.addActionListener(this);
        p.add(pen);
        controlPanel.add(p);

        p = new JPanel();
        color = new JButton("Color");
        color.addActionListener(this);
        p.add(color);
        controlPanel.add(p);

        JFrame frame = new JFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setDefaultCloseOperation(3);
        frame.setTitle("Turtle Graphics");
        frame.setSize(500, 300);

        this.setLayout((new GridLayout(1, 2)));
        this.add(controlPanel);
        this.add(view);

        frame.setVisible(true);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "SaveAs", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", new String[]{"North", "South", "East", "West", "Clear", "Pen", "Color"}, this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmmd = e.getActionCommand();
        if (cmmd == "North") {
            int steps = Integer.parseInt(Utilities.ask("How many steps?"));
            turtle.turn(Heading.NORTH);
            turtle.move(steps);
        } else if (cmmd == "East") {
            int steps = Integer.parseInt(Utilities.ask("How many steps?"));
            turtle.turn(Heading.EAST);
            turtle.move(steps);
        } else if (cmmd == "West") {
            int steps = Integer.parseInt(Utilities.ask("How many steps?"));
            turtle.turn(Heading.WEST);
            turtle.move(steps);
        } else if(cmmd == "South") {
            int steps = Integer.parseInt(Utilities.ask("How many steps?"));
            turtle.turn(Heading.SOUTH);
            turtle.move(steps);
        } else if(cmmd == "Clear") {
            turtle.clear();
        } else if(cmmd == "Pen") {
            turtle.setPenUp(!turtle.getPenUp());
        } else if (cmmd == "Color") {
            Color newColor = JColorChooser.showDialog(null, "Pick a color", turtle.getColor());
            turtle.setColor(newColor);
        } else if (cmmd == "Save") {
            try {
                String fName = Utilities.getFileName((String)null, false);
                ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                os.writeObject(this.turtle);
                os.close();
            } catch (Exception var6) {
                Utilities.error(var6);
            }
        } else if (cmmd != "SaveAs") {
            if (cmmd == "Open") {
                try {
                    String fName = Utilities.getFileName((String)null, true);
                    ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                    this.turtle = (Turtle)is.readObject();
                    this.view.setTurtle(turtle);
                    is.close();
                } catch (Exception var5) {
                    Utilities.error(var5.getMessage());
                }
            } else if (cmmd == "New") {
                turtle = new Turtle();
                this.view.setTurtle(turtle);
            } else if (cmmd == "Quit") {
                System.exit(1);
            } else if (cmmd == "About") {
                Utilities.inform("Cyberdellic Designs Turtle Graphics, 2021. All rights reserved.");
            } else if (cmmd == "Help") {
                Utilities.inform("Move the turtle with the buttons");
            } else {
                Utilities.error("Unrecognized command: " + cmmd);
            }
        }


    }





    public static void main(String[] args) {
        TurtlePanel app = new TurtlePanel();
        //app.display();
    }
}
