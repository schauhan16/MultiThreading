package com.shailendra.threadcreation;

class Runner implements Runnable {
    public void run(){
        for (int i = 0; i < 10; i++) {
            System.out.println("Hello " + i);
            try{
                Thread.sleep(100);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
}

public class ThreadCreationByRunnable{
    public static void main(String[] args) {
        Runner runner = new Runner();
        Thread t1 = new Thread(runner);
        Thread t2 = new Thread(runner);

        t1.start();
        t2.start();
    }
}