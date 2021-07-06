package com.example.databinding.simple2;

/**
 * Created  by Administrator on 2021/6/19
 */
public class SuperStar1 {

    public String name;
    public int star;//喜欢程度 几颗心 这里改为int通过StarUtils去转换成字符串

    public SuperStar1(String name, int star) {
        this.name = name;
        this.star = star;
    }
}
