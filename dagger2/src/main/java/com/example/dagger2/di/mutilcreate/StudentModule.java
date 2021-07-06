package com.example.dagger2.di.mutilcreate;

import javax.inject.Named;
import dagger.Module;
import dagger.Provides;

/**
 * Created  by Administrator on 2021/6/29
 * 同一对象多种创建方式
 */
@Module
public class StudentModule {

    // @Named指定标识 不同的值不同的标识
    @Named("student1")
    @Provides
    Student provideStudent(){
        return new Student();
    }

    @Named("student2")
    @Provides
    Student provideStudent2(){
        return new Student("cat tom");
    }
}
