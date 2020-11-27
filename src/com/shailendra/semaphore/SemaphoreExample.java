package com.shailendra.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class Connection {
    
    private static Connection instance = new Connection();
    private int connections = 0;
    private Semaphore semaphore = new Semaphore(10);

    private Connection() {
    }

    public static Connection getInstance() {
        return instance;
    }

    public void connect() throws InterruptedException {
        semaphore.acquire();
        synchronized (this) {
            connections++;
            System.out.println("Current connections: " + connections);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this) {
            connections--;
        }
        semaphore.release();
    }
}


public class SemaphoreExample {
    public static void main(String[] args) throws InterruptedException {
        Connection connection = Connection.getInstance();
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 100; i++) {
            executor.submit(new Runnable() {
                public void run() {
                    try {
                        connection.connect();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }   
}