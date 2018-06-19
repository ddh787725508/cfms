package com.acat.spring.boot.blog.repository;

import com.acat.spring.boot.blog.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Vote Repository接口
 */
public interface VoteRepository extends JpaRepository<Vote,Long> {
}
