package com.acat.spring.boot.blog.service.impl;

import com.acat.spring.boot.blog.domain.Comment;
import com.acat.spring.boot.blog.repository.CommentRepository;
import com.acat.spring.boot.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * comment 服务的实现类
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Override
    @Transactional
    public void removeComment(Long id) {
            commentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Comment getComment(Long id) {
        return commentRepository.findById(id).get();
    }
}
