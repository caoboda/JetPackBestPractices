package com.example.room.simple2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用Room+ViewModel+LiveData对simple里面的代码进行优化
 * 问题：每当数据库发生变化时都需要重新去查询，然后重新开启一个线程去获取数据库数据
 * 解决：当数据变化时，通过LiveData通知View层更新UI，实现数据自动更新
 */
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StudentDao studentDao;
    private StudentRecycleViewAdapter adapter;
    private StudentViewModel studentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         recyclerView= findViewById(R.id.recycleview);
        Button button = findViewById(R.id.button4);
        button.setText("clearAll");
         recyclerView.setLayoutManager(new LinearLayoutManager(this));
         List<Student> students=new ArrayList<>();
         adapter= new StudentRecycleViewAdapter(students);
         recyclerView.setAdapter(adapter);
      //  studentDao= MyDatabase.getmInstance(this).getStudentDao();
         studentViewModel= new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(StudentViewModel.class);
         studentViewModel.getAllStudentLiveData().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                adapter.setStudent(students);
                adapter.notifyDataSetChanged();
            }
        });
    }

    //room不允许在主线程操作数据库(增删改查)
    public void insert(View view) {
        Student student1=new Student("zhangshan",22);
        Student student2=new Student("lisi",24);
      //  new InsertStudentTask(studentDao).execute(student1,student2);
        studentViewModel.insert(student1,student2 );
    }

    public void delete(View view) {
        Student student=new Student(1);
       // new DeleteStudentTask(studentDao).execute(student);
        studentViewModel.delete(student);

    }


    public void update(View view) {
        Student student=new Student(2,"xxx",8);
       // new UpdateStudentTask(studentDao).execute(student);
        studentViewModel.update(student);
    }


    public void query(View view) {
        //这里调用清空的方法 没改ui 在查询里面搞的
       // new QueryStudentTask(studentDao).execute();
        studentViewModel.deleteAll();
    }


   /* class InsertStudentTask extends AsyncTask<Student,Void,Void>{
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
    }*/
}