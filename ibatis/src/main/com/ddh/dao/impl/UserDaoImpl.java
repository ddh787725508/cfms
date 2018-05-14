package com.ddh.dao.impl;

import com.ddh.dao.UserDao;
import com.ddh.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;




/**
 * Created by ddh on 2018/5/10.
 */

public class UserDaoImpl implements UserDao {
    //注入
    private SqlSessionFactory sqlSessionFactory;
    public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    //通过用户ID查询一个用户
    public User selectUserById(Integer id){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.selectOne("user.findUserById", id);
    }
}
