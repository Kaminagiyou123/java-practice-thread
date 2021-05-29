package com.company;

public class Worker {
    private String name;
    private boolean active;

    public Worker(String name, boolean active) {
        this.name = name;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }
    public synchronized void work(SharedResource sharedResource, Worker otherworker){
        while(active){
            if(sharedResource.getOwner()!=this){
                try{
                    wait(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            if (otherworker.isActive()){
                System.out.println(getName()+" : give the resource to the worker "+ otherworker.getName());
                sharedResource.setOwner(otherworker);
                continue;
            }
            System.out.println(getName()+" : working on the comment resource");
            active=false;
            sharedResource.setOwner(otherworker);
        }
    }
}
