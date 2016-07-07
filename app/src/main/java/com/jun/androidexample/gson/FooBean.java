package com.jun.androidexample.gson;

import java.util.Date;

/**
 * Created by Jun on 2016/7/7.
 */
public class FooBean {
    /**
     * id : 100
     * body : It is my post
     * number : 0.13
     * created_at : 2014-05-22 19:12:38
     */

    private int id;
    private String body;
    private double number;

    // Date类型
    private Date created_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
