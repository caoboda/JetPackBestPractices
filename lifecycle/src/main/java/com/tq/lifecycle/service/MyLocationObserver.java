package com.tq.lifecycle.service;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * Created by cbd on 2021/6/17 18:19
 */
public class MyLocationObserver implements LifecycleObserver {
    private Context mContext;
    private LocationManager locationManager;
    private MyLocationListener myLocationListener;

    public MyLocationObserver(Context mContext) {
        this.mContext = mContext;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void startGetLocation() {
        Log.e("gps= ","startGetLocation");
        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        myLocationListener = new MyLocationListener();
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 1, myLocationListener);
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void stopGetLocation(){
        Log.e("gps= ","stopGetLocation");
        locationManager.removeUpdates(myLocationListener);
    }

    static class MyLocationListener implements LocationListener{

        @Override
        public void onLocationChanged(@NonNull Location location) {
            Log.e("gps= ",location.toString());
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {

        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {

        }
    }

}
