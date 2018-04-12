package com.ddh.Serializable;

import java.io.*;

/**
 * Created by ddh on 2018/4/12.
 */
@SuppressWarnings("all")
public class ObejectIntputStreamDemo {
    public static void main(String[] args) throws Exception {
        File file=new File("D:"+File.separator+"perosn.ddh");
        ObjectInputStream ois=null;
        ois=new ObjectInputStream((new FileInputStream(file)));
       // Person person=new Person("ddh",12);
        Person person=(Person)ois.readObject();
        System.out.println(person);
        ois.close();
    }
}
