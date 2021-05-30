package com.company.Lambda;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.*;

public class Predicates {

    public static void main(String[] args) {
        Employee john = new Employee("John Doe", 23);
        Employee tim = new Employee("Tim Wan", 21);
        Employee jack = new Employee("Jack Markman", 33);
        Employee jone = new Employee("Jone Kelly", 36);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(jone);

        //function

        Function<Employee, String> getLastName= (e)->
            e.getName().substring(e.getName().indexOf(" ")+1);
        String lastName=getLastName.apply(employees.get(1));
        System.out.println(lastName);

        //chained function

        Function<Employee,String> upperCase=e->e.getName().toUpperCase();
        Function<String,String> firstName=name->name.substring(0,name.indexOf(" "));
        Function chainedFunction=upperCase.andThen(firstName);
        System.out.println(chainedFunction.apply(employees.get(0)));

        //biFunction

        BiFunction<String, Employee,String> concatAge=(String name,Employee e)->
           name.concat(" "+e.getAge());
        String upperName=upperCase.apply(employees.get(0));
        System.out.println(concatAge.apply(upperName,employees.get(0)));

        // unary
        IntUnaryOperator incBy5=i->i+5;
        System.out.println(incBy5.applyAsInt(15));

        Consumer<String> c1=s->s.toUpperCase();
        Consumer<String> c2=s-> System.out.println(s);
        c1.andThen(c2).accept("hello world");


}





//        printEmployeesByAge(employees,employee -> employee.getAge()>30);
//        printEmployeesByAge(employees,employee -> employee.getAge()<30);
//        printEmployeesByAge(employees,new Predicate<Employee>(){
//
//            @Override
//            public boolean test(Employee employee) {
//                return employee.getAge()<25;
//            }
//        });

//        employees.forEach(e->
//                System.out.println("The last name is "+e.getName().substring(e.getName().indexOf(" ")+1)));
//

//        IntPredicate GreaterThan15=i->i>15;
//        IntPredicate LessThan100= i->i<20;
//        System.out.println(GreaterThan15.test(10));
//
//        int a=10;
//        System.out.println(GreaterThan15.test(a+5));
//        System.out.println(GreaterThan15.and(LessThan100).test(25));
//
//        Random random=new Random();
//        Supplier<Integer> randomSupplier=()->random.nextInt(100);
//        for (int i=0;i<10;i++){
//            System.out.println(randomSupplier.get());
//        }


    private static void printEmployeesByAge(List<Employee> employees, Predicate<Employee> ageCondition){
        System.out.println("===========");
        for (Employee employee:employees){
            if (ageCondition.test(employee)){
                System.out.println(employee.getName());
            }
        }

    }
}
