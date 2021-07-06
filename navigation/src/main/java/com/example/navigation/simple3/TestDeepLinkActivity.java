package com.example.navigation.simple3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;

import com.example.navigation.R;
/**
 * 深层链接DeepLink
 * PendingIntent方式
 * 当App收到某个通知推送，我们希望用户在点击该通知时 能够直接跳转到展示该通知内容的页面，可以通过PendingIntent来完成
 * URL方式
 * 当用户通过手机浏览器浏览网站上某个页面，可以在网页上放置一个类似于"在应用内打开"的按钮，如果用户的手机安装有我们的App，那么通过DeepLink就能打开相应的界面，
 * 如果没有安装，那么网站可以导航到应用程序的下载页面，引导用户安装应用程序。
 * 模拟器使用：adb shell am start -a android.intent.action.View -d "http://www.baidu.com/fromWeb"
 */
public class TestDeepLinkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_deep_link);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController= Navigation.findNavController(this,R.id.fragment3);
        return navController.navigateUp();
    }

}