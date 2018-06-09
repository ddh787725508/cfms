package com.acat.service.impl;

import com.acat.mapper.UserInfoMapper;
import com.acat.po.UserInfo;
import com.acat.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户管理的业务逻辑实现
 * @Author ddh
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
   @Autowired
    private UserInfoMapper userInfoMapper;


    public boolean add(UserInfo userInfo) {
        int i=userInfoMapper.insertSelective(userInfo);
        if (i>0) return true;
        else return false;
    }

    public List<UserInfo> getUserList(UserInfo record) {
                if (record!=null&&record.getUserName()!=null&&!record.getUserName().equals("")){
                    record.setUserName("%"+record.getUserName()+"%");
                }
        return userInfoMapper.getUserList(record);
    }

    public boolean update(UserInfo user) {
        int i=userInfoMapper.updateByPrimaryKeySelective(user);
        if (i>0){
            return true;
        }else {
            return false;
        }
    }

    public boolean delete(String userNumber) {
        int i=userInfoMapper.deleteByPrimaryKey(userNumber);
        if (i>0){
            return true;
        }else{
            return false;
        }
    }

    public UserInfo getUser(String userNumber) {
        return userInfoMapper.selectByPrimaryKey(userNumber);
    }
}
