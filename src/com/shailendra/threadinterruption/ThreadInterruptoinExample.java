package com.shailendra.threadinterruption;

import java.util.Random;

public class ThreadInterruptoinExample {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable(){
            public void run() {
                Random random = new Random();
                System.out.println("Starting");
                for (int i = 0; i < 1E8; i++) {
                    try {
                    Thread.sleep(1);
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted!");
                        break;
                    }

                    Math.sin(random.nextDouble());
                }            
            }
        });

        thread.start();
        thread.interrupt();
        thread.join();

        System.out.println("Finished!");
    }
    
}