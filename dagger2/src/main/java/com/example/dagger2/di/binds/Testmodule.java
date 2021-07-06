package com.example.dagger2.di.binds;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created  by Administrator on 2021/6/29
 *   使用@Binds注入接口的实例
 */
@Module
public abstract class Testmodule {

    //@Binds注入接口的实例
    @Binds
    abstract  AInterface  bindAInterface(AInterfaceImp02 impl);

    //抽象类中使用static否则报错？？
    @Provides
    static AInterfaceImp01 provideAInterfaceImp01(){
        return new AInterfaceImp01();
    }

    @Provides
    static AInterfaceImp02 provideAInterfaceImp02(){
        return new AInterfaceImp02();
    }
}
