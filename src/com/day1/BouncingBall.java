package com.day1;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BouncingBall extends JFrame implements Runnable {

    Thread thread;
    JLabel ballLable = new JLabel();
    ImageIcon icon = new ImageIcon(new ImageIcon("src/com/day1/smallball.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
    Random random = new Random();

    int startX = random.ints(0,600).findFirst().getAsInt();
    int startY = random.ints(0,600).findFirst().getAsInt();

    public BouncingBall() {
        this.setTitle("Bouncing ball Application :)");

        this.add(ballLable);
        thread = new Thread(this);
        thread.start();
    }

    public static void main(String[] args) {
        BouncingBall app = new BouncingBall();
        app.setBounds(50, 50, 600, 600);
        app.setVisible(true);
    }

    @Override
    public void run() {
        // Add motion logic here
        while (true) {
            ballLable.setIcon(icon);
            ballLable.setLocation(ballLable.getX() + 10, ballLable.getY()-10);

            System.out.println("x= " + ballLable.getX());

            if (ballLable.getY() < 0) {
                ballLable.setLocation(0, ballLable.getY());

            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
