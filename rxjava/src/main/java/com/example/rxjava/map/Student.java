package com.example.rxjava.map;

import java.util.List;

/**
 * Created  by Administrator on 2021/7/4
 */
public class Student {
    public String name;
    public List<Course> courseList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    static class Course{
        public String courseName;

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        @Override
        public String toString() {
            return "Course{" +
                    "courseName='" + courseName + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", courseList=" + courseList +
                '}';
    }
}
