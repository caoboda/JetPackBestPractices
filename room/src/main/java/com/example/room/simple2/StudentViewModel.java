package com.example.room.simple2;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

/**
 * Created  by Administrator on 2021/6/20 00:14
 */
public class StudentViewModel extends AndroidViewModel {
    StudentRespository studentRespository;

    public StudentViewModel(@NonNull  Application application) {
        super(application);
        studentRespository=new StudentRespository(application);
    }


    public void insert(Student... students) {
        studentRespository.insert(students);
    }

    public void delete(Student... students) {
        studentRespository.delete(students);
    }

    public void deleteAll() {
        studentRespository.deleteAll();
    }


    public void update(Student... students) {
        studentRespository.update(students);
    }

    //获取所有
    public LiveData<List<Student>> getAllStudentLiveData(){
        return studentRespository.getAllStudentLiveData();
    }
}
