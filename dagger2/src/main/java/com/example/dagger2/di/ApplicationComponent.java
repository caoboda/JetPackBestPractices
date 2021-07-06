package com.example.dagger2.di;

import com.example.dagger2.MainActivity;
import com.example.dagger2.MainActivity2;
import com.example.dagger2.di.binds.Testmodule;
import com.example.dagger2.di.mutilcreate.StudentModule;
import javax.inject.Singleton;
import dagger.Component;

/**
 * Created  by Administrator on 2021/6/28
 * 第二步：ApplicationComponent相当于IOC容器 里面的对象实例注入到目标类中
 */
@Singleton//注意：组件上的作用域必须和你所在模块的作用域一致(或者都不使用作用域，默认不写)
         // 如果使用@Score报错（NetModule里面的作用域都改为@Score就可以了）
@Component(modules = {NetModule.class, Testmodule.class, StudentModule.class})
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);
    void inject(MainActivity2 mainActivity);

}
