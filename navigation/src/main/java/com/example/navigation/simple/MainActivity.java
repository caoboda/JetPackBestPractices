package com.example.navigation.simple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.navigation.R;

/**
 * Navigation诞生的原因
 * 平时一般使用FragmentManager和FragmentTranstion来管理Fragment的切换
 * Navigation的出现是为了方便我们管理Fragment和App Bar
 *
 *  Navigation的主要元素
 *  1.Navigation Graph 是一种新的Xml资源文件，包含应用程序所有的页面，及页面之间的关系。
 *  2.NavigationHostFragment 一个特殊的Fragment,可以看作其他Fragment的容器，
 *    Navigation Graph中的Frgment通过NavigationHostFragment进行展示。
 *  3.NavController 用于在代码中完成NNavigation Graph中具体页面的切换工作。
 *  三者的关系 使用NavController进行页面的切换吗，告诉它你要去Navigation Graph中的哪个Fragment，
 *  NavController将你想去的Fragment展示在NavigationHostFragment中
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavController navController= Navigation.findNavController(this,R.id.fragment);
        NavigationUI.setupActionBarWithNavController(this,navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController=Navigation.findNavController(this,R.id.fragment);
        return navController.navigateUp();
    }
}