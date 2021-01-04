package com.day1;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class DateTimeApp extends JFrame implements Runnable {

    Thread th;
    Date d = new Date();
    JLabel timeLable = new JLabel();

    public DateTimeApp() {

        this.setTitle("Date and time app using threads");
        timeLable.setHorizontalAlignment(JLabel.CENTER);
        timeLable.setText(d.toString());
        this.add(timeLable, BorderLayout.CENTER);
        th = new Thread(this);
        th.start();
        // this.add(board);
    }

    public static void main(String[] args) {

        DateTimeApp app = new DateTimeApp();
        app.setBounds(50, 50, 600, 400);
        app.setVisible(true);

    }

    @Override
    public void run() {
        while (true) {
            d = new Date();
            try {
                timeLable.setText(d.toString());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}