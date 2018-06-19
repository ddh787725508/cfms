package com.acat.spring.boot.blog.service;

import com.acat.spring.boot.blog.domain.Comment;

/**
 * comment 服务接口
 */
public interface CommentService {
    /**
     * 删除评论
     * @param id
     */
   void removeComment(Long id);

    /**
     * 添加评论
     * @param id
     * @return
     */
   Comment getComment(Long id);
}
