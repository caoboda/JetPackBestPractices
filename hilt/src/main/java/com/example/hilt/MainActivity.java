package com.example.hilt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import dagger.Module;
import dagger.hilt.android.AndroidEntryPoint;

/**Hilt
 * 1、Hilt中文意思是刀柄,而Dagger是利器
 * 2、Hilt是基于Dagger构建而成的依赖注入框架，它提供了一种将Dagger纳入android应用的标准方法，使用Hilt提供的规范来实现依赖注入，可以避免很多不必要的问题
 *
 * Hilt的作用
 * 1、简化了Dagger的使用，大量减少了使用Dagger时编写的重复代码(Module和Component)
 * 2、Hilt提供饿了一套标准组件和作用域注解，不必再自定义组件和作用域。
 * 3、Hilt提供几种预定义的绑定(提供对象实例的获取称为绑定)：如：Application实例或者Activity实例的获取
 * 4、Hilt提供集几种预定义的限定符（Qualifier）,@ApplicationContext和ActivityContext获取ApplicationContext
 *
 * Hilt默认标准组件
 * Hilt生成了几种默认的标准组件，并为其指定了作用域。每个Android类都有一个关联的Hilt组件，这赋予了这些作用域实际的含义。
 * Hilt组件                     注入器面向的对象                      作用域
 * ApplicationComponent        Application                     @Singleton
 * ActivityRetainedComponent   ViewModel                       @ActivityRetainedScoped
 * ActivityComponent           Activity                        @ActivityScoped
 * FragmentComponent           Fragment                        @FragmentScoped
 * ServiceComponent            Service                         @ServiceScoped
 * ViewComponent               View                            @ViewScoped
 * ViewWithFragmentComponent   @WithFragmentBindings 的View    @ViewScoped
 *
 * Hilt默认绑定
 * 每个Hilt组件都附带有一组默认绑定、Hilt可以将其作为依赖项注入到自定义的绑定。
 * 注意：这些绑定对应于常规Activity和Fragment类型，而不对应于任何子类
 *  Hilt组件                     默认绑定
 *  ApplicationComponent         Application
 *  ActivityRetainedComponent    Application
 *  ActivityComponent            Application和Activity
 *  FragmentComponent            Application、Activity和Fragment
 *  ServiceComponent             Application和Service
 *  ViewComponent                Application、Activity和View
 *  ViewWithFragmentComponent    Application、Activity、Fragment 和View
 *
 *  Hilt实现依赖注入的步骤：
 *  1、为项目添加自定义的Application，并使用@HiltAndroidApp注解，@HiltAndroidApp用来生成相应的组件
 *  2、建立绑定：构造方法使用@Inject提供对象实例，如果是Dagger模块（模块类即@Module注解的类）来提供对象的实例，
 *     则需要在模块类上使用@InstallIn注解将模块装载到相应组件(Hilt提供了默认的7个标准组件)。
 *  3、在目标类上使用@AndroidEntryPoit注解，通过@Inject注解注入对象实例。
 */
//@AndroidEntryPoint  相当于行注入动作DaggerApplicationComponent.create().inject(this);
//@AndroidEntryPoint注解的Activity必须是ComponentActivity的子类
@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    @Inject
    User user1;

    @Inject
    User user2;

    @Inject
    Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("user1= ",user1.toString());
        Log.e("user2= ",user2.toString());
        model.test();
        startActivity(new Intent(MainActivity.this,MainActivity2.class));
        //MyViewModel里面的被 @ViewModelInject注解的构造方法就通过Hilt自动注入(从而获取参数构造函数中的参数的值)而不是手动调用构造方法去创建
        MyViewModel myViewModel=new ViewModelProvider(this).get(MyViewModel.class);
        myViewModel.test();
    }
}