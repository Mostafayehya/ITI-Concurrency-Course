package com.day1;

import javax.swing.*;
import java.awt.*;

public class TextBannerApp extends JFrame implements Runnable {

    Thread thread;
    String message = "Concurrency Rocks!!";
    JLabel marquee = new JLabel();
    ImageIcon icon = new ImageIcon(new ImageIcon("src/com/day1/smallball.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));


    public TextBannerApp() {
        this.setTitle("Banner Application :)");
     //   marquee.setHorizontalAlignment(JLabel.CENTER);

        marquee.setText(message);
        this.add(marquee);
        thread = new Thread(this);
        thread.start();
    }

    public static void main(String[] args) {
        TextBannerApp app = new TextBannerApp();
        app.setBounds(50, 50, 600, 600);
        app.setVisible(true);
    }

    @Override
    public void run() {
        // Add motion logic here
        while (true) {
       //     marquee.setIcon(icon);
            marquee.setLocation(marquee.getX() + 10, marquee.getY());

            System.out.println("x= " + marquee.getX());
            if (marquee.getX() > this.getWidth()) {
                marquee.setLocation(0, marquee.getY());

            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
