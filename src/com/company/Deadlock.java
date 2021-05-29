package com.company;

public class Deadlock {
    public static Object lock1= new Object();
    public static Object lock2= new Object();

    public static void main(String[] args) {
        Thread t1=new Thread1();
        Thread t2= new Thread2();
        t1.start();
        t2.start();

    }

    private static class Thread1 extends Thread{
        @Override
        public void run() {
            synchronized (lock1){
                System.out.println("Thread1: Has lock 1");
                try{
                    Thread.sleep(100);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Thread1:Waiting for lock 2");
                synchronized (lock2){
                    System.out.println("Thread1: Has lock 1 and lock 2");
                }
                System.out.println("Thread1: released lock2");

            }
            System.out.println("Thread1: Released lock1. Exiting...");
        }
    }

    private static class Thread2 extends Thread{
        @Override
        public void run() {
            synchronized (lock1){
                System.out.println("Thread2: has lock 1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread2:waiting for lock 2 ");
                synchronized (lock2){
                    System.out.println("Thread2:Has lock 2 and lock1");
                }
                System.out.println("Thread2:Released lock2");
            }
            System.out.println("Thread2:Released lock1.Exiting...");
        }
    }
}


