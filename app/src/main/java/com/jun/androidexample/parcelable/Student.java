package com.jun.androidexample.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 实现Parcelable接口
 */
public class Student implements Parcelable {

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

    // 直接返回一个0即可
    @Override
    public int describeContents() {
        return 0;
    }

    // 将name和age写入Parcel对象中
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }

    // 创造器
    public static final Creator<Student> CREATOR = new Creator<Student>() {

        // 将name和age从Parcel对象中读取出来
        @Override
        public Student createFromParcel(Parcel in) {
            Student student = new Student();
            student.name = in.readString();
            student.age = in.readInt();
            return student;
        }

        // 返回一个数组
        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
