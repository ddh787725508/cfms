package com.acat.spring.boot.blog.service;

import com.acat.spring.boot.blog.domain.Vote;

/**
 * Vote Service接口
 */
public interface VoteService {
    /**
     * 根据Id获取Vote
     * @param id
     */
  Vote getVoteById(Long id);

    /**
     * 删除vote
     * @param id
     */
  void removeVote(Long id);
}
