package com.jun.androidexample.serializable;

import java.io.Serializable;

/**
 * 实现Serializable接口
 */
public class Person implements Serializable {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
