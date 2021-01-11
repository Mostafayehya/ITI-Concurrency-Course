package com.day2;

import java.util.ArrayList;
import java.util.List;

class Data {
    public static final List<Integer> result = new ArrayList<>(30);

    synchronized static void add(List<Integer> list) {
        result.addAll(list);
    }
}
class DataAdder implements Runnable {

    List<Integer> data = new ArrayList<>(10);
    Thread thread;

    public DataAdder(int value) {
        data.add(value);
        data.add(value);
        data.add(value);
        data.add(value);
//        Collections.fill(data, value);

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        Data.add(data);
    }
}

public class SynchronizedArrayCollector {


    public static void main(String... args) {

        new DataAdder(1);
        new DataAdder(2);
        new DataAdder(3);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Data.result);

    }
}



