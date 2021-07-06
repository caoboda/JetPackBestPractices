package com.example.room.simple;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

/**
 * Created  by Administrator on 2021/6/19 21:44
 */
@Dao
public interface StudentDao {

    @Insert
    void insertStudent(Student...students);

    @Delete
    void deleteStudent(Student...students);

    @Update
    void updateStudent(Student...students);

    @Query("SELECT * FROM student")
    List<Student> getAllStudent();

    @Query("SELECT * FROM student WHERE id=:id")
    Student getStudentById(int id);

}