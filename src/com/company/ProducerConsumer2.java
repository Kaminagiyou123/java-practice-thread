package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import static com.company.ProducerConsumer2.EOF;

public class ProducerConsumer2 {
    public static final String EOF="EOF";
    public static void main(String[] args) {
        List <String> b= new ArrayList<String>();
        ReentrantLock bufferLock= new ReentrantLock();
        MyProducer producer= new MyProducer(b,ThreadColor.ANSI_GREEN,bufferLock);
        MyConsumer consumer1=new MyConsumer(b,ThreadColor.ANSI_PURPLE,bufferLock);
        MyConsumer consumer2= new MyConsumer(b, ThreadColor.ANSI_CYAN,bufferLock);

        new Thread(consumer1).start();
        new Thread(consumer2).start();
        new Thread(producer).start();

    }
}

class MyProducer implements Runnable{
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public MyProducer(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }

    @Override
    public void run() {
        Random random= new Random();
        String[] nums={"1","2","3","4","5"};
        for (String num:nums){
            try{ bufferLock.lock();
                try {
                    System.out.println(color + "adding " + num);
                    buffer.add(num);
                } finally{
                    bufferLock.unlock();
                }

                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        bufferLock.lock();
        try{
            System.out.println(color+"Adding EOF and Exiting");

            buffer.add("EOF");
        } finally {
            bufferLock.unlock();
        }
    }
}

class MyConsumer implements Runnable{
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public MyConsumer(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }

    @Override
    public void run() {
        int counter=0;
        while (true){
            if (bufferLock.tryLock())
            { try{
                if (buffer.isEmpty()) {
                    continue;
                }
                System.out.println(color+ " The counter= "+counter);
                counter=0;

                if (buffer.get(0).equals(EOF)) {
                    System.out.println(color + "Exiting");
                    break;
                } else {
                    System.out.println(color + "removed " + buffer.remove(0));
                }
            } finally{
                bufferLock.unlock();
            }
            } else{
                counter++;
            }
        }
        }
    }
