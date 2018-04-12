package com.ddh.Serializable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created by ddh on 2018/4/12.
 */
public class ObejectOutputStreamDemo {
    public static void main(String[] args) throws Exception {
        File file=new File("D:"+File.separator+"perosn.ddh");
        ObjectOutputStream oos=null;
        oos=new ObjectOutputStream(new FileOutputStream(file));
        Person person=new Person("ddh",12);
        oos.writeObject(person);
        oos.close();

    }
}
