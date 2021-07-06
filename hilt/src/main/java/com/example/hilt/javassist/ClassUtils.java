package com.example.hilt.javassist;

import java.lang.reflect.Method;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;

/**
 * Created  by Administrator on 2021/7/3
 */
public class ClassUtils {

    public static void ModifyClass(){
        try{
            ClassPool classPool=ClassPool.getDefault();
           CtClass studentctClass= classPool.get("com.example.hilt.javassist.Student");
            CtClass personctClass= classPool.get("com.example.hilt.javassist.Person");
            studentctClass.setSuperclass(personctClass);
            studentctClass.addField(CtField.make("private String name;",studentctClass));
            studentctClass.addMethod(CtMethod.make("private void setName(String name){this.name=name;}",studentctClass));
            studentctClass.addMethod(CtMethod.make("private String getName(){return this.name;}",studentctClass));
            //转成class字节码
            Class studentClass=studentctClass.toClass();
            //反射操作字节码
            Method setNameMethod=studentClass.getDeclaredMethod("setName",String.class);
            Method getNameMethod=studentClass.getDeclaredMethod("getName");
            //getMethod可拿到父类的方法
            Method getPersonNameMethod=studentClass.getMethod("getPersonName");
            setNameMethod.setAccessible(true);
            getNameMethod.setAccessible(true);
            getPersonNameMethod.setAccessible(true);

            Student student= (Student) studentClass.newInstance();
            setNameMethod.invoke(student,"我是一个小学生？");
            //获取名称
            String name= (String) getNameMethod.invoke(student);
            System.out.println(name);
            //调用父类方法
            String personName= (String) getPersonNameMethod.invoke(student);
            System.out.println(personName);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
