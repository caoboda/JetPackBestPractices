package com.example.navigation.simple3;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.navigation.R;
import com.example.navigation.simple.HomeFragmentArgs;


public class PendingIntentFragment extends Fragment {


    private int notificationId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pending_intent, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        Button send_btn=getView().findViewById(R.id.send_btn);
        send_btn.setOnClickListener(v -> {
          sendNotification();
        });
    }

    private void sendNotification() {
        //8.0通知渠道
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel(getActivity().getPackageName(),"my_channel", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("my notificationChannel");
            NotificationManager notificationManager=getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        Notification notification= new NotificationCompat.Builder(getContext(),getActivity().getPackageName())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Deep Link")
                .setContentText("点我...")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(getPendingIntent())
                .build();
        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(getActivity());
        notificationManagerCompat.notify(notificationId++,notification);
    }

    //通过DeepLink创建PendingIntent
    private PendingIntent getPendingIntent() {
        Bundle args=new Bundle();
        args.putString("user_name","DeepLink createDeepLink createDeepLink");
        return Navigation.findNavController(getActivity(),R.id.send_btn)
                .createDeepLink()
                .setGraph(R.navigation.deep_link_fgraph)
                .setDestination(R.id.detailFragment2)
                .setArguments(args)
                .createPendingIntent();
    }
}