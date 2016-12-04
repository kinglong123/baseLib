package com.kinglong.baseapp.mybaseapp.data.model;

import java.io.Serializable;

/**
 * Created by lanjl on 2016/12/4.
 */

public class UserInfo implements Serializable {

    private String name;

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
