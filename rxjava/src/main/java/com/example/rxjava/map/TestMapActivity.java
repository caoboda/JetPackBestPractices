package com.example.rxjava.map;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.example.rxjava.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * map操作符的使用
 */
public class TestMapActivity extends AppCompatActivity {
    public   final   String TAG=this.getClass().getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_map);

        //模拟从后台获取数据
        //获取Student的课程信息
        // 1、一般的使用方式
        //testgetStudent();
        //2、使用map处理方式
      //  testgetStudentByMap();
        //3、使用flatmap处理方式
        testgetStudentByFlatMap();
    }



    private List<Student> initStudentList() {
        List<Student> studentList=new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            Student student=new Student();
            List<Student.Course> courseList=new ArrayList<>();
            for (int j = 1; j <3 ; j++) {
                Student.Course course=new Student.Course();
                course.setCourseName("course"+j);
                courseList.add(course);
            }
            student.setName("student"+i);
            student.setCourseList(courseList);
            studentList.add(student);
        }
        return studentList;
    }

    private void testgetStudent() {
        new Thread(new Runnable() {
            @Override
            public void run() {
               List<Student> studentList= initStudentList();
                for (Student student : studentList) {
                    List<Student.Course> courseList= student.getCourseList();
                    for (Student.Course course : courseList) {
                        Log.e(TAG,course.toString());
                    }
                }
            }
        }).start();
    }

    @SuppressLint("CheckResult")
    private void testgetStudentByMap() {
        Observable.fromIterable(initStudentList())
                //1、使用map把Student转换成List<Student.Course>
                .map(new Function<Student, List<Student.Course>>() {
                    @Override
                    public List<Student.Course> apply(@NotNull Student student) throws Exception {
                        return student.getCourseList();
                    }
                }).subscribe(new Consumer<List<Student.Course>>() {
            @Override
            public void accept(List<Student.Course> courseslist) throws Exception {
                //2、这里还是需要循环遍历 效果和一般的使用方式（testgetStudent方式）一样 所以我们使用flatmap处理
                for (Student.Course course : courseslist) {
                    Log.e(TAG,course.toString());
                }
            }
        });
    }

    @SuppressLint("CheckResult")
    private void testgetStudentByFlatMap() {
        Observable.fromIterable(initStudentList())
                .flatMap(new Function<Student, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NotNull Student student) throws Exception {
                        //通过Observable.fromIterable把Student.Course一个一个发送
                        return Observable.fromIterable(student.getCourseList());
                    }
                }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.e(TAG,o.toString());
            }
        });


    }


}