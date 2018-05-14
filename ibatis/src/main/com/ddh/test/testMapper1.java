package com.ddh.test;

import com.ddh.mapper.UserMapper;
import com.ddh.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by ddh on 2018/5/11.
 */
public class testMapper1 {
     private static ApplicationContext context;
    @BeforeClass
    public void before(){
      context=new ClassPathXmlApplicationContext("applicationContext.xml");
    }
    @Test
    public void test(){
       UserMapper userMapper=  context.getBean(UserMapper.class);
       User user= userMapper.findUserById(10);
        System.out.println(user);
    }

}
