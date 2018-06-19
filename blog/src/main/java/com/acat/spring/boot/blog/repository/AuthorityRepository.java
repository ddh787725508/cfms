package com.acat.spring.boot.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acat.spring.boot.blog.domain.Authority;

/**
 * Authority Repository 仓库
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long>{
}
