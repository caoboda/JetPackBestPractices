package com.example.room.simple;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Created  by Administrator on 2021/6/19 21:37
 */
@Entity(tableName = "student")
public class Student {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id",typeAffinity=ColumnInfo.INTEGER)
    public int id;

    @ColumnInfo(name="name",typeAffinity=ColumnInfo.TEXT)
    public  String name;

    @ColumnInfo(name="age",typeAffinity=ColumnInfo.INTEGER)
    public  int age;

    //room用这个构造方法 不会管@Ignore的构造方法
    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

   //@Ignore注解的我们自己使用
    @Ignore
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //@Ignore注解的我们自己使用
    @Ignore
    public Student(int id) {
        this.id = id;
    }
}
