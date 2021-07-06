package com.example.dagger2.ioc;

/**
 * Created  by Administrator on 2021/6/28
 * 手动依赖注入对象
 */
public class Car {
    Engine engine;

    //直接newengine对象，一般不这么做，Engine构造方法变动需改动
    public Car( ) {
        this.engine = new Engine() ;
    }


    //构造方法注入engine对象
    public Car(Engine engine) {
        this.engine = engine;
    }

    //set方法注入engine对象(字段注入)
    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
