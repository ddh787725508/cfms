package com.ddh.test;


import com.ddh.dao.UserDao;
import com.ddh.dao.impl.UserDaoImpl;
import com.ddh.pojo.User;
import org.apache.ibatis.io.Resources;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import org.testng.annotations.Test;


import java.io.InputStream;

/**
 * Created by ddh on 2018/5/10.
 */
public class testDao {
    public SqlSessionFactory sqlSessionFactory;
    @org.testng.annotations.BeforeClass
    public void before() throws Exception {
        //加载核心配置文件
        String resource = "sqlMapConfig.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        //创建SqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
    }
    @Test
    public void testDao() throws Exception {

        UserDao userDao = new UserDaoImpl(sqlSessionFactory);

        User user = userDao.selectUserById(10);
        System.out.println(user);
    }
}
