package com.company;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private double balance;
    private String accountNumber;
    private Lock lock;

    public BankAccount(double balance, String accountNumber) {
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.lock=new ReentrantLock();

    }
    public void deposit(double amount){
        boolean status=false;
        try{
            if(lock.tryLock(1000,TimeUnit.MILLISECONDS)){
                try{
                    balance+=amount;
                    status=true;
                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Transaction status ="+status);


    }
    public  void withdraw(double amount){
        boolean status=false;
       try{
           if(lock.tryLock(2000,TimeUnit.MILLISECONDS)){
               try{
                   balance-=amount;
                   status=true;
               } finally {
                   lock.unlock();
               }
           }
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
        System.out.println("Transaction status = "+status);

    }
    public String getAccountNumber(){
        return accountNumber;
    }
    public void printAccountNumber(){
        System.out.println("The account number is "+accountNumber);
    }
}
