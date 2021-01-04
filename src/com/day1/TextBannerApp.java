package com.day1;

import javax.swing.*;
import java.awt.*;

public class TextBannerApp extends JFrame implements Runnable {

    Thread thread;
    String message = "Concurrency Rocks!!";
    JLabel marquee = new JLabel();

    public TextBannerApp() {
        this.setTitle("Banner Application :)");
        marquee.setHorizontalAlignment(JLabel.CENTER);
        marquee.setText(message);
        this.add(marquee, BorderLayout.CENTER);
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
            marquee.setText(message);
            marquee.setLocation(marquee.getX() + 10, marquee.getY());
            if (marquee.getX() == (this.getWidth() / 2)) {
                marquee.setLocation(-this.getWidth() / 2, marquee.getY());
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
