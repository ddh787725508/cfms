package com.acat.spring.boot.blog.service.impl;

import com.acat.spring.boot.blog.domain.User;
import com.acat.spring.boot.blog.repository.UserRepository;
import com.acat.spring.boot.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * 用户服务接口实现
 */
@Service
public class UserServiceImpl implements UserService,UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    @Transactional
    public User saveOrUpdateUser(User user) {
        return userRepository.save(user);
    }
     @Transactional
    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }
        @Transactional
    @Override
    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public Page<User> listUsersByNameLike(String name, Pageable pageable) {
        name="%"+name+"%";
        return userRepository.findByNameLike(name,pageable);
    }

    @Override
    public List<User> listUsersByUsernames(Collection<String> usernames) {
        return userRepository.findByUsernameIn(usernames);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username);
    }
}
