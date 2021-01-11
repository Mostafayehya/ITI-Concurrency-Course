package com.day2;

import java.util.Arrays;

public class ArrayCollector {


    public static void main(String... args) throws InterruptedException {
        int[] arr1 = new int[10];
        int[] arr2 = new int[10];
        int[] arr3 = new int[10];
        int[] resultArr = new int[30];

        Arrays.fill(arr1, 1);
        Arrays.fill(arr2, 2);
        Arrays.fill(arr3, 3);

        Thread t1 = new Thread(() -> {
            System.out.println("Adding first arr elements");
            for (int i = 0; i < arr1.length; i++)
                resultArr[i] = arr1[i];
        });
        Thread t2 = new Thread(() -> {
            System.out.println("Adding second arr elements");
            for (int i = 0; i < arr2.length; i++)
                resultArr[i + 10] = arr2[i];
        });
        Thread t3 = new Thread(() -> {
            System.out.println("Adding third arr elements");
            for (int i = 0; i < arr3.length; i++)
                resultArr[i + 20] = arr3[i];
        });

        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();

        Arrays.stream(resultArr).forEach(i -> System.out.print(i));
    }
}
