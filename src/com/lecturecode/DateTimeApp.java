package com.lecturecode;

import javax.swing.*;
import java.util.Date;

public class DateTimeApp extends JFrame implements Runnable {

    Thread th;
    Date d = new Date();


    public DateTimeApp(){

        this.setTitle(d.toString());
        th = new Thread(this);
        th.start();
       // this.add(board);
    }
    public static void main(String[] args) {

            DateTimeApp app = new DateTimeApp();
            app.setBounds(50,50,600,400);
            app.setVisible(true);

    }

    @Override
    public void run() {
        while(true){
            d = new Date();
            try {
                Thread.sleep(1000);
                this.setTitle(d.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
