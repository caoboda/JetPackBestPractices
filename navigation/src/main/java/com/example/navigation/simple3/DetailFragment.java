package com.example.navigation.simple3;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.example.navigation.R;


public class DetailFragment extends Fragment {
    public String TAG= DetailFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button button=getView().findViewById(R.id.button2);
        //接收参数
        Bundle args= getArguments();
        if(args!=null){
            String user_name=args.getString("user_name");
            if(!TextUtils.isEmpty(user_name)){
                Log.e(TAG,user_name);
            }
            String params=args.getString("params");
            if(!TextUtils.isEmpty(params)){
                Log.e(TAG,params);
            }
        }

       button.setOnClickListener(v -> {
            NavController navController= Navigation.findNavController(v);
            navController.navigate(R.id.action_detailFragment2_to_pendingIntentFragment);
        });
    }
}