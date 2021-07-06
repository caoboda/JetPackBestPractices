package com.example.dagger2.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * Created  by Administrator on 2021/6/29
 * 自定义作用域
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface MyScore {
}
