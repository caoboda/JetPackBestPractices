package com.example.navigation.simple2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.example.navigation.R;

/**
 * NavigationUI应用
 */

public class TestNavigationUIActivity extends AppCompatActivity {

    private NavController navController;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_navigation);
         navController= Navigation.findNavController(this,R.id.fragment2);
         appBarConfiguration= new AppBarConfiguration.Builder(navController.getGraph()).build();
         NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
    }

    //
    @Override
    public boolean onSupportNavigateUp() {
        //SettingFragment 返回按键起作用
        return NavigationUI.navigateUp(navController,appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //创建菜单
        getMenuInflater().inflate(R.menu.menu_setting,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //选择菜单
        return NavigationUI.onNavDestinationSelected(item,navController) || super.onOptionsItemSelected(item);
    }
}