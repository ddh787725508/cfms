package com.acat.service;

import com.acat.po.UserInfo;

import java.util.List;

/**
 * 用户管理的业务逻辑接口
 */
public interface UserInfoService {
    /**
     * 添加用户信息
     * @param userInfo
     * @return
     */
    public boolean add(UserInfo userInfo);

    /**
     * 查询用户信息
     * @param record
     * @return
     */
    List<UserInfo> getUserList(UserInfo record);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    boolean update(UserInfo user);

    /**
     * 删除用户信息
     * @param userNumber
     * @return
     */
    public boolean delete(String userNumber);

    /**
     * 得到user
     * @param userNumber
     * @return
     */
    public UserInfo getUser(String userNumber);
}
