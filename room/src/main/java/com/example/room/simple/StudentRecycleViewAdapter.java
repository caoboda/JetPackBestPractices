package com.example.room.simple;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room.R;

import java.util.List;
/**
 * Created  by Administrator on 2021/6/19 17:46
 */
public class StudentRecycleViewAdapter extends RecyclerView.Adapter<StudentRecycleViewAdapter.MyViewHolder> {


    private List<Student> studentList;

    public StudentRecycleViewAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview=LayoutInflater.from(parent.getContext()).inflate( R.layout.item,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Student student=studentList.get(position);
        TextView id_tv=holder.itemView.findViewById(R.id.id_tv);
        TextView name_tv=holder.itemView.findViewById(R.id.name_tv);
        TextView age_tv=holder.itemView.findViewById(R.id.age_tv);
        id_tv.setText(""+student.id);
        name_tv.setText(""+student.name);
        age_tv.setText(""+student.age);

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public void setStudent(List<Student> studentList) {
        this.studentList=studentList;
    }

    static class  MyViewHolder extends RecyclerView.ViewHolder{
        View itemView;


        public MyViewHolder(View itemview) {
            super(itemview);
            this.itemView=itemview;
        }
    }
}
