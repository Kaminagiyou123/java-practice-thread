package com.company.Lambda;

import java.util.*;

public class LambdaMain {
    public static void main(String[] args) {
        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Tim Wan", 21);
        Employee jack = new Employee("Jack Markman", 23);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);

       employees.forEach(e->{
           System.out.println(e.getName());
           System.out.println(e.getAge());
       });

        UpperConcat us=(s1,s2)-> {
            String result=s1.toUpperCase() + " " + s2.toUpperCase();
            return result;
        };

        String sillyString=doStringstuff(us, employees.get(0).getName(),employees.get(1).getName());
        System.out.println(sillyString);

        AnotherClass anotherClass= new AnotherClass();
        String s=anotherClass.doSomething();
        System.out.println(s);
    }

    public final static String doStringstuff(UpperConcat uc, String s1, String s2){
        return uc.upperAndConcat(s1,s2);
    }
}
class Employee {
    private String name;
    private int age;

    public Employee(String name, int age)  {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

}

@FunctionalInterface
interface UpperConcat{
     String upperAndConcat(String s1,String s2);
}

class AnotherClass{
    public String doSomething(){
          int i=0;
        UpperConcat uc=(s1,s2)->{
            System.out.println("The lambda expression's class is "+getClass().getSimpleName());
            String result=s1.toUpperCase()+s2.toUpperCase();
            return result;
        };

            System.out.println("The Another Class class's name is "+getClass().getSimpleName());
            System.out.println("i= "+i);
            return LambdaMain.doStringstuff(uc,"String1","String2");
        }

}