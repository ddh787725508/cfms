package com.ddh.Serializable;

import java.io.Serializable;

/**
 * Created by ddh on 2018/4/12.
 */
public class Person implements Serializable {
  private String name;
  private int age;
    public Person(String name,int age){
        this.age=age;
        this.name=name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
