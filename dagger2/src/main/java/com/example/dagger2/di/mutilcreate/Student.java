package com.example.dagger2.di.mutilcreate;

/**
 * Created  by Administrator on 2021/6/29
 */
public class Student {
    private String name="tom";

    public Student() {

    }

    public Student(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
