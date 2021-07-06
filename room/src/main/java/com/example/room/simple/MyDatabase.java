package com.example.room.simple;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Created  by Administrator on 2021/6/19 21:48
 * 必须为抽象类
 */
@Database(entities = {Student.class},version = 1,exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    private  static  final  String DATABASE_NAME="stu_db.db";
    private  static  MyDatabase mInstance;
    //不能私有化构造方法 因为我们的room要根据构造方法创建我们的MyDatabase实现类
   // private MyDatabase(){}


    public static synchronized MyDatabase getmInstance(Context context) {
        if(mInstance==null){
            mInstance= Room.databaseBuilder(context.getApplicationContext(),
                    MyDatabase.class,
                    DATABASE_NAME)
                   // .allowMainThreadQueries()//允许在主线程中查询
                    .build();
        }
        return mInstance;
    }

    //提供一个获取StudentDao的抽象方法 room自动帮我们实现
    public abstract StudentDao getStudentDao();
}
