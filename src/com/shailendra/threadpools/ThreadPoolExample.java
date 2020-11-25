package com.shailendra.threadpools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {

    private int id;

    Processor(int id) {
        this.id = id;
    }

    public void run() {
        System.out.println("Starting : " + id);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Completed : " + id);
    }
}

public class ThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 10; i++) {
            executorService.submit(new Processor(i));
        }
        
        executorService.shutdown();

        System.out.println("All tasks Submitted!!!");

        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("All tasks completed!!!");
    }
}