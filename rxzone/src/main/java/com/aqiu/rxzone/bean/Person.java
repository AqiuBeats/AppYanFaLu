package com.aqiu.rxzone.bean;

/**
 * Created by aqiu on 2017/3/6.
 */

public class Person {
    /**
     * name : aqiu
     * age : 18
     */

    private String name;
    private String age;

    public Person(String name, String age) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
