package com.day2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class PausableBouncingBall extends JPanel {

    JButton playButton = new JButton("Play");
    JButton pauseButton = new JButton("Pause");

    volatile boolean playFlag = true;

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

    public PausableBouncingBall() {

        add(playButton);
        add(pauseButton);
        playButton.addActionListener(actionEvent -> playFlag = true);
        pauseButton.addActionListener(actionEvent -> playFlag = false);

        Thread thread = new Thread(() -> {
            while (true) {

                width = getWidth();
                height = getHeight();

                // update location
                if(playFlag == true) {
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
        });
        thread.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

            g.drawImage(img, (int) (X - radius), (int) (Y - radius), (int) diameter, (int) diameter, (image, i, i1, i2, i3, i4) -> true);

    }
    public static void main(String[] args) {

        JFrame frame = new JFrame("Bouncing Ball");
        frame.setSize(600, 600);
        frame.setContentPane(new PausableBouncingBall());
        frame.setVisible(true);

    }
}
