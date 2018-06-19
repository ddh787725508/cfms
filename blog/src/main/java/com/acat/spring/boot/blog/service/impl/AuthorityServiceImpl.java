package com.acat.spring.boot.blog.service.impl;


import com.acat.spring.boot.blog.domain.Authority;
import com.acat.spring.boot.blog.repository.AuthorityRepository;
import com.acat.spring.boot.blog.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Authority 服务接口
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityRepository authorityRepository;
    @Override
    public Authority getAuthorityById(Long id) {
        return authorityRepository.findById(id).get();
    }
}
