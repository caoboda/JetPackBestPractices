package com.example.databinding.simple6;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.databinding.R;
import com.example.databinding.databinding.ItemBinding;
import java.util.List;

/**
 * Created  by Administrator on 2021/6/19 17:46
 */
public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.MyViewHolder> {


    private List<User> userList;

    public MyRecycleViewAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBinding itemBinding=DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item,parent,false);
        return new MyViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecycleViewAdapter.MyViewHolder holder, int position) {
        User user=userList.get(position);
        holder.itemBinding.setUser(user);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class  MyViewHolder extends RecyclerView.ViewHolder{
        ItemBinding itemBinding;

        public MyViewHolder(@NonNull ItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding=itemBinding;
        }
    }
}
