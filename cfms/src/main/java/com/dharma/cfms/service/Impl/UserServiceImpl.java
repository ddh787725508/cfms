package com.dharma.cfms.service.Impl;

import com.dharma.cfms.domain.User;
import com.dharma.cfms.repository.UserRepository;
import com.dharma.cfms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    @Transactional
    public User saveOrUpdateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User registerUser(User user) {
        return null;
    }

    @Override
    public void removeUser(Long id) {

    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public Page<User> listUserByNameLike(String name, Pageable pageable) {
        return null;
    }

    @Override
    public List<User> listUserByUsername(Collection<String> username) {
        return null;
    }
}
