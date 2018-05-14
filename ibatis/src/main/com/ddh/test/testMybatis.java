package com.ddh.test;

import com.ddh.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by ddh on 2018/5/10.
 */
@SuppressWarnings("all")
public class testMybatis {
    @Test
    public void test1() throws IOException {
        String resoucre="sqlMapConfig.xml";
      InputStream in= Resources.getResourceAsStream(resoucre);
         SqlSessionFactory sqlSessionFactory=   new SqlSessionFactoryBuilder().build(in);
       SqlSession sqlSession= sqlSessionFactory.openSession();
        User user=sqlSession.selectOne("user.findUserById",10);
        System.out.println(user);
    }
    @Test
    public void test2() throws IOException {
        String resoucre="sqlMapConfig.xml";
        InputStream in= Resources.getResourceAsStream(resoucre);
        SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession= sqlSessionFactory.openSession();
       List <User> users=sqlSession.selectList("user.findUserByName","王");
        System.out.println(users);
    }
    @Test
    public void test3() throws IOException {
        String resoucre="sqlMapConfig.xml";
        InputStream in= Resources.getResourceAsStream(resoucre);
        SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession= sqlSessionFactory.openSession();
         User user=new User();
        user.setUsername("aaaa");
        user.setAddress("西安");
        user.setBirthday(new Date());
        user.setSex("男");
        int i=sqlSession.insert("user.insertUser",user);
        sqlSession.commit();
        System.out.println(i);
        System.out.println(user.getId());
    }
    @Test
    public void test4() throws IOException {
        String resoucre="sqlMapConfig.xml";
        InputStream in= Resources.getResourceAsStream(resoucre);
        SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession= sqlSessionFactory.openSession();
        User user=new User();
         user.setId(29);
        user.setUsername("bbb");
        user.setAddress("西安");
        user.setBirthday(new Date());
        user.setSex("男");
        int i=sqlSession.update("user.updateUserById",user);
        sqlSession.commit();
        System.out.println(i);

    }
    @Test
    public void test5() throws IOException {
        String resoucre="sqlMapConfig.xml";
        InputStream in= Resources.getResourceAsStream(resoucre);
        SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession= sqlSessionFactory.openSession();
        int i=sqlSession.update("user.deleteUserById",29);
        sqlSession.commit();
        System.out.println(i);

    }
}
