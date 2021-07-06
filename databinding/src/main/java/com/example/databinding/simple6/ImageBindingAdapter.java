package com.example.databinding.simple6;

import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import com.example.databinding.R;

/**
 * Created  by Administrator on 2021/6/19 15:53
 */
public class ImageBindingAdapter {

    //加载网络图片
    @BindingAdapter("itemImage")
    public static void setImage(ImageView imageView,String url){
        Glide.with(imageView)
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);
    }

}
