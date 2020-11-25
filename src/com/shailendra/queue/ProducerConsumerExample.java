package com.shailendra.queue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerExample {

    private Random random;
    private BlockingQueue<Integer> queue;

    public ProducerConsumerExample() {
        random = new Random();
        queue = new ArrayBlockingQueue<>(10);

    }

    public static void main(String[] args) throws InterruptedException{
        ProducerConsumerExample pcExample = new ProducerConsumerExample();
        Thread t1 = new Thread(new Runnable(){
            public void run(){
                try {
                  pcExample.producer();
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable(){
            public void run(){
                try {
                  pcExample.consumer();
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
    

    private void producer() throws InterruptedException {
        while (true) {
            queue.put(random.nextInt(100));
        }
    }

    private void consumer() throws InterruptedException {
        while (true) {
            Thread.sleep(100);
            if (random.nextInt(10) == 0) {
                Integer value = queue.take();
                System.out.println("Taken value: " + value + "; Queue size: " + queue.size());
            }
        }
    }
}