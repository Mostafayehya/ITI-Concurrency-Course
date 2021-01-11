package com.day3;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BouncingBallWithExecutors extends JPanel {


    volatile boolean playFlag = true;
    Executor executor = Executors.newFixedThreadPool(4);

    JButton playButton = new JButton("Play");
    JButton pauseButton = new JButton("Pause");

    String message = "Executors Rocks!!";
    JLabel marquee = new JLabel();

    Date d = new Date();
    JLabel timeLable = new JLabel();

    // Box height and width
    int width;
    int height;

    // Ball Size
    float radius = 15;
    float diameter = radius * 2;
    Random random = new Random();
    int startX = random.ints(0, 600).findFirst().getAsInt();
    int startY = random.ints(0, 600).findFirst().getAsInt();
    // Center of Call
    float X = radius + (float) startX;
    float Y = radius + (float) startY;

    // Direction
    float dx = 2;
    float dy = 3;
    Image img = new ImageIcon("src/com/day1/smallball.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);

    public BouncingBallWithExecutors() {

        marquee.setText(message);
        marquee.setMaximumSize(new Dimension(50,50));
        marquee.setHorizontalAlignment(JLabel.CENTER);
        timeLable.setText(d.toString());
     //   timeLable.set


        add(playButton);
        add(pauseButton);
        add(marquee,BorderLayout.CENTER);

        JPanel timeDatePanel = new JPanel(new BorderLayout());
        timeDatePanel.add(timeLable,BorderLayout.SOUTH);
        //add(timeDatePanel,BorderLayout.SOUTH);


        playButton.addActionListener(actionEvent -> playFlag = true);
        pauseButton.addActionListener(actionEvent -> playFlag = false);

        Runnable drawBallTask = () -> {
            while (true) {

                width = getWidth();
                height = getHeight();

                // update location
                if (playFlag == true) {
                    X = X + dx;
                    Y = Y + dy;
                }

                if (X - radius < 0) {  // Hitting the left side
                    dx = -dx;
                    X = radius;
                } else if (X + radius > width) { // Hitting the right side
                    dx = -dx;
                    X = width - radius;
                }

                if (Y - radius < 0) { // Hitting upper side
                    dy = -dy;
                    Y = radius;
                } else if (Y + radius > height) { // Hitting lower side
                    dy = -dy;
                    Y = height - radius;
                }
                repaint();

                try {
                    Thread.sleep(5);
                } catch (InterruptedException ex) {
                }

            }
        };

        Runnable showMarqueeTask = () ->{
            while (true) {

                marquee.setLocation(marquee.getX() + 10, marquee.getY());
                System.out.println("x= " + marquee.getX());

                if (marquee.getX() > this.getWidth()) {
                    System.out.println(marquee.getWidth());
                    marquee.setLocation(-105, marquee.getY());

                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable updateDateTask = () -> {
            while (true) {
                d = new Date();
                try {
                    timeLable.setText(d.toString());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        executor.execute(updateDateTask);
        executor.execute(drawBallTask);
        executor.execute(showMarqueeTask);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(img, (int) (X - radius), (int) (Y - radius), (int) diameter, (int) diameter, (image, i, i1, i2, i3, i4) -> true);

    }


    public static void main(String[] args) {

        JFrame frame = new JFrame("Bouncing Ball");
        frame.setSize(600, 600);

        frame.setContentPane(new BouncingBallWithExecutors());
        frame.setVisible(true);

    }
}
