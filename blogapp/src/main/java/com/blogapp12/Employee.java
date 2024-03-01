package com.blogapp12;

public class Employee {
    private long id;
    private String name;
    private long salary;

    public Employee(long id, String name, long salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
    public long getId(){
        return  id;
    }
    public String getName(){
        return name;
    }
    public long getSalary(){
        return salary;
    }
}