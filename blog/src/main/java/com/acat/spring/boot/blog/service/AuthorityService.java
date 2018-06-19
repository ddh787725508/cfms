package com.acat.spring.boot.blog.service;

import com.acat.spring.boot.blog.domain.Authority;

public interface AuthorityService {
    /**
     * 根据ID 查询权限
     * @param id
     * @return
     */
     Authority getAuthorityById(Long id);
}
