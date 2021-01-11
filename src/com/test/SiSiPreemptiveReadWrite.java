package com.test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SiSiPreemptiveReadWrite {
    ReadWriteLock lock2 = new ReentrantReadWriteLock();
    String color = "red";

    void setColor(String x) {
        lock2.writeLock().lock();
        System.out.println("Setting Color");
  //      try {
          //  Thread.sleep(1000);
            System.out.println("Still setting Color");
         //   Thread.sleep(1000);
            System.out.println("Still setting Color");

   /*     } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        color = x;
        System.out.println("color set to " + color);
        lock2.writeLock().unlock();
    }

    void getColor() {
        lock2.readLock().lock();
        System.out.println("Getting Color");
        try {
            Thread.sleep(4000);
            System.out.println("Still Getting Color");
            Thread.sleep(4000);
            System.out.println("Still Getting Color");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(color);
        lock2.readLock().unlock();
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

        SiSiPreemptiveReadWrite test = new SiSiPreemptiveReadWrite();

        new Thread(test::getColor).start();            //reading starts first
     //   Thread.sleep(200);
        new Thread(() -> test.setColor("blue")).start();   // writing won't start executing until reading finishes.

    }

}
