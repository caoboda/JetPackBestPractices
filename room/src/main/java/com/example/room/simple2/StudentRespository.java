package com.example.room.simple2;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Created  by Administrator on 2021/6/20 00:15
 */
public class StudentRespository {

     private  StudentDao studentDao;

     public StudentRespository(Context context){
         this.studentDao=  MyDatabase.getmInstance(context).getStudentDao();
     }

     public void insert(Student... students) {
          new InsertStudentTask(studentDao).execute(students);
     }

     public void delete(Student... students) {
          new DeleteStudentTask(studentDao).execute(students);
     }

     public void deleteAll() {
          new DeleteAllStudentTask(studentDao).execute();
     }


     public void update(Student... students) {
          new UpdateStudentTask(studentDao).execute(students);
     }

    //获取所有
     public LiveData<List<Student>> getAllStudentLiveData(){
          return studentDao.getAllStudentLiveData();
     }

     class InsertStudentTask extends AsyncTask<Student,Void,Void> {
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

     class DeleteAllStudentTask extends AsyncTask<Void,Void,Void>{
          private StudentDao studentDao;

          public DeleteAllStudentTask(StudentDao studentDao) {
               this.studentDao = studentDao;
          }

          @Override
          protected Void doInBackground(Void... voids) {
               studentDao.deleteAllStudent();
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





}
