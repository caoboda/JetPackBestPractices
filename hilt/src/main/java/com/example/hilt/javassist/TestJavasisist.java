package com.example.hilt.javassist;

/**
 * Created  by Administrator on 2021/7/3
 * Javassist是一个开源的分析、编辑和创建Java字节码的类库，可以直接使用Java编码的形式，而不需要了解虚拟机指令，就能动态改变类的结构(修改或增加代码)，
 * 或者动态生成类。Javassist 是一个执行字节码操作的库。它可以在一个已经编译好的类中添加新的方法，或者是修改已有的方法，并且不需要对字节码方面有深入的了解。
 * Javassist 可以绕过编译，直接操作字节码，从而实现代码注入，所以使用 Javassist 的时机就是在构建工具 Gradle 将源文件编译成 .class 文件之后，在将 .class 打包成 dex 文件之前(Hilt就是这样生成的类)。
 */
public class TestJavasisist {

    public static void main(String[] args) {
        ClassUtils.ModifyClass();
    }
}
