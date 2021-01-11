package com.day3;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class MarqueeExecutor extends JFrame implements Runnable {


    Executor executor = Executors.newFixedThreadPool(5);
    String message = "Executors Rocks!!";
    JLabel marquee = new JLabel();


    public MarqueeExecutor() {
        this.setTitle("Marquee Application - Using Executors");

        marquee.setText(message);
        marquee.setMaximumSize(new Dimension(50,50));
   //     marquee.setBorder(BasicBorders.getProgressBarBorder());

        this.add(marquee);
        executor.execute(this);
    }

    public static void main(String[] args) {
        MarqueeExecutor app = new MarqueeExecutor();
        app.setBounds(50, 50, 600, 600);
        app.setVisible(true);
    }

    @Override
    public void run() {
        // Add motion logic here
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

    }
}

