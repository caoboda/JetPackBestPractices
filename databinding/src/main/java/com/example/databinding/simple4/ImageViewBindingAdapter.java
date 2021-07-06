package com.example.databinding.simple4;

import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.databinding.R;

/**
 * Created  by Administrator on 2021/6/19 15:53
 */
public class ImageViewBindingAdapter {

    //加载网络图片
    @BindingAdapter("imageurl")
    public static void setImage(ImageView imageView,String url){
        Glide.with(imageView)
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);
    }

    //方法重载
    //加载本地图片
    @BindingAdapter("imageurl")
    public static void setImage(ImageView imageView,int resId){
        imageView.setImageResource(resId);
    }

    //多参数重载
    //参数可选 当网络图片为null时 加载本地图片
    @BindingAdapter(value = {"imageurl","defaultImageResource"},requireAll = false)
    public static void setImage(ImageView imageView,String url,int resId){
        if(!TextUtils.isEmpty(url)){
            Glide.with(imageView)
                    .load(url)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(imageView);
        }else{
            imageView.setImageResource(resId);
        }

    }
}
