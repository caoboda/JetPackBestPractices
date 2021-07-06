package com.example.room.simple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.room.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StudentDao studentDao;
    private StudentRecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         recyclerView= findViewById(R.id.recycleview);
         recyclerView.setLayoutManager(new LinearLayoutManager(this));
         List<Student> students=new ArrayList<>();
        adapter= new StudentRecycleViewAdapter(students);
        recyclerView.setAdapter(adapter);
        studentDao= MyDatabase.getmInstance(this).getStudentDao();
    }

    //room不允许在主线程操作数据库(增删改查)
    public void insert(View view) {
        Student student1=new Student("zhangshan",22);
        Student student2=new Student("lisi",24);
        new InsertStudentTask(studentDao).execute(student1,student2);
    }

    public void delete(View view) {
        Student student=new Student(1);
        new DeleteStudentTask(studentDao).execute(student);
    }


    public void update(View view) {
        Student student=new Student(2,"xxx",8);
        new UpdateStudentTask(studentDao).execute(student);
    }


    public void query(View view) {
        new QueryStudentTask(studentDao).execute();
    }


    class InsertStudentTask extends AsyncTask<Student,Void,Void>{
        private StudentDao studentDao;

        public InsertStudentTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.insertStudent(students);
            return null;
        }
    }

    class DeleteStudentTask extends AsyncTask<Student,Void,Void>{
        private StudentDao studentDao;

        public DeleteStudentTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.deleteStudent(students);
            return null;
        }
    }

    class UpdateStudentTask extends AsyncTask<Student,Void,Void>{
        private StudentDao studentDao;

        public UpdateStudentTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.updateStudent(students);
            return null;
        }
    }

    class QueryStudentTask extends AsyncTask<Student,Void,Void>{
        private StudentDao studentDao;

        public QueryStudentTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
           List<Student> studentList= studentDao.getAllStudent();
            adapter.setStudent(studentList);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            //主线程更新
            adapter.notifyDataSetChanged();
        }
    }
}