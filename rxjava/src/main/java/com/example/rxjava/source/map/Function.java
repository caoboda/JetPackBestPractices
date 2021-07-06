package com.example.rxjava.source.map;

/**
 * Created  by Administrator on 2021/7/4
 *
 */
public interface Function<T,R> {
    //把T转换成R
    R apply(T t);
}
