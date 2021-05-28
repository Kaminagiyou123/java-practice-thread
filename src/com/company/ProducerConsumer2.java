package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.company.ProducerConsumer2.EOF;

public class ProducerConsumer2 {
    public static final String EOF="EOF";
    public static void main(String[] args) {
        List <String> b= new ArrayList<String>();
        MyProducer producer= new MyProducer(b,ThreadColor.ANSI_GREEN);
        MyConsumer consumer1=new MyConsumer(b,ThreadColor.ANSI_PURPLE);
        MyConsumer consumer2= new MyConsumer(b, ThreadColor.ANSI_CYAN);



        new Thread(consumer1).start();
        new Thread(consumer2).start();
        new Thread(producer).start();


    }
}

class MyProducer implements Runnable{
    private List<String> buffer;
    private String color;

    public MyProducer(List<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        Random random= new Random();
        String[] nums={"1","2","3","4","5"};
        for (String num:nums){
            try{
                System.out.println(color+"adding "+num);
                  synchronized (buffer) {
                      buffer.add(num);
                  };
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(color+"Adding EOF and Exiting");
        synchronized (buffer) {
            buffer.add("EOF");
        }
    }
}

class MyConsumer implements Runnable{
    private List<String> buffer;
    private String color;

    public MyConsumer(List<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }
    @Override
    public void run() {
        while (true){
            synchronized (buffer) {
                if (buffer.isEmpty()) {
                    continue;
                }
                if (buffer.get(0).equals(EOF)) {
                    System.out.println(color + "Exiting");
                    break;
                } else {
                    System.out.println(color + "removed " + buffer.remove(0));
                }
            }
        }
    }
}