package com.acat.spring.boot.blog.service;

import com.acat.spring.boot.blog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

/**
 * 用户服务接口
 */
public interface UserService {
    /**
     * 新增、编辑、保存用户
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
     *  删除用户
     * @param id
     */
    void removeUser(Long id);

    /**
     * 根据id 获得用户
     * @param id
     * @return
     */
    User getUserById(Long id);
    Page<User> listUsersByNameLike(String name, Pageable pageable);
    /**
     * 更具名称列表查询
     * @param usernames
     * @return
     */
    List<User> listUsersByUsernames(Collection<String> usernames);
}
