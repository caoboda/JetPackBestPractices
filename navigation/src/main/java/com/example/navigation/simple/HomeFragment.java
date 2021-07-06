package com.example.navigation.simple;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.navigation.R;

/**
 * Navigation 基本跳转、 普通传参 、safe args传参 、动画
 */
public class HomeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable  Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //普通传参
       /* Bundle args=new Bundle();
        args.putString("user_name","asdf");*/

       //safe args传参
       Bundle args= new HomeFragmentArgs.Builder()
         .setUserName("fffff")
        .setAge(22)
        .build().toBundle();
        Button button=getView().findViewById(R.id.button);
        button.setOnClickListener(v -> {
           NavController navController= Navigation.findNavController(v);
            navController.navigate(R.id.action_homeFragment_to_detailFragment,args);
        });
    }
}