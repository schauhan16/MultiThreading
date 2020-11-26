package com.shailendra.waitnotify;

import java.util.LinkedList;
import java.util.Random;

public class ManualImplementation {

    private LinkedList<Integer> list = new LinkedList<>();
    private static final int LIMIIT = 10;
    private Object lock = new Object();

    private void producer() throws InterruptedException {
        int count = 0;
        while (true) {
            synchronized (lock) {
                while (list.size() == LIMIIT) {
                    lock.wait();
                }
                list.add(count++);
                lock.notify();
            }
        }
    }
    
    private void consumer() throws InterruptedException {
        Random random = new Random();
        while (true) {
            synchronized (lock) {
                if (list.size() == 0) {
                    lock.wait();
                }
                System.out.print("List size: " + list.size());
                int value = list.removeFirst();
                System.out.println("; Value:" + value);
                lock.notify();
            }

            Thread.sleep(random.nextInt(500));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ManualImplementation processor = new ManualImplementation();
        Thread t1 = new Thread(new Runnable(){

          public void run() {
            try {
              processor.producer();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        });

        Thread t2 = new Thread(new Runnable(){

          public void run() {
            try {
              processor.consumer();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}