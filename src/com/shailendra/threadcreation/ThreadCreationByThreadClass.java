package com.shailendra.threadcreation;

class ThreadRunner extends Thread {
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

public class ThreadCreationByThreadClass{
    public static void main(String[] args) {
        Thread t1 = new ThreadRunner();
        Thread t2 = new ThreadRunner();

        t1.start();
        t2.start();
    }
}