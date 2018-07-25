package com.dharma.cfms.service;

import com.dharma.cfms.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface UserService {
    /**
     * 新增编辑保存用户
     * @param user
     * @return
     */
    User saveOrUpdateUser(User user);

    /**
     * 注册用户
     * @param user
     * @return
     */
    User registerUser(User user);

    /**
     * 删除用户
     * @param id
     */
    void removeUser(Long id);

    /**
     * 根据ID获得用户
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 根据名字模糊查询用户
     * @param name
     * @param pageable
     * @return
     */
    Page<User> listUserByNameLike(String name, Pageable pageable);

    /**
     * 根据名称查新列表
     * @param username
     * @return
     */
    List<User> listUserByUsername(Collection<String> username);
}
