package com.acat.spring.boot.blog.repository;

import com.acat.spring.boot.blog.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * comment 仓库
 */
public interface CommentRepository extends JpaRepository<Comment ,Long> {
}
