package com.acat.spring.boot.blog.service.impl;

import com.acat.spring.boot.blog.domain.Vote;
import com.acat.spring.boot.blog.repository.VoteRepository;
import com.acat.spring.boot.blog.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Vote ServiceImpl 接口实现
 */
@Service
public class VoteServiceImpl implements VoteService {
    @Autowired
    private VoteRepository voteRepository;
    @Override
    public Vote getVoteById(Long id) {
       return voteRepository.findById(id).get();
    }

    @Override
    public void removeVote(Long id) {
        voteRepository.deleteById(id);
    }
}
