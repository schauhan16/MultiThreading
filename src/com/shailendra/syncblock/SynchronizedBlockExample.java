package com.shailendra.syncblock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class SynchronizedBlockExample {

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();
    private Random random = new Random();

    Object lock1 = new Object();
    Object lock2 = new Object();

    private void stageOne() {
        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(random.nextInt());
        }
    }
    
    private void stageTwo() {
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list2.add(random.nextInt());
        }
    }
    
    private void process() {
        for (int i = 0; i < 1000; i++) {
            stageOne();
            stageTwo();
        }
    }
    
    public void main() {

        long startTime = System.currentTimeMillis();
        Thread t1 = new Thread(new Runnable(){
            public void run() {
                process();
            }
        });

        Thread t2 = new Thread(new Runnable(){
            public void run() {
                process();
            }
        });

        t1.start();
        t2.start();

        
        try {
            t1.join();
			t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        long endTime = System.currentTimeMillis();

        System.out.println("Time taken: " + (endTime - startTime));
        System.out.println("List 1: " + list1.size() + "; List 2: " + list2.size());
    }
    
}