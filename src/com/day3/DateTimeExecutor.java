package com.day3;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DateTimeExecutor extends JFrame {

    Executor executor = Executors.newSingleThreadExecutor();
    Date d = new Date();
    JLabel timeLable = new JLabel();

    public DateTimeExecutor() {
        this.setTitle("Date and time app using Executors");
        timeLable.setHorizontalAlignment(JLabel.CENTER);
        timeLable.setText(d.toString());
        this.add(timeLable, BorderLayout.CENTER);

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
    }

    public static void main(String[] args) {
        DateTimeExecutor app = new DateTimeExecutor();
        app.setBounds(50, 50, 600, 400);
        app.setVisible(true);

    }


}