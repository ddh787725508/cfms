package com.ddh.dao;

import com.ddh.pojo.User;

/**
 * Created by ddh on 2018/5/10.
 */
public interface UserDao {
    User selectUserById(Integer id);
}
