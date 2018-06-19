package com.acat.spring.boot.blog.service;

import com.acat.spring.boot.blog.domain.Blog;
import com.acat.spring.boot.blog.domain.Catalog;
import com.acat.spring.boot.blog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



/**
 * blog 服务接口
 */
@Service
public interface BlogService {
    /**
     * 保存blog
     * @param blog
     * @return
     */
       Blog saveBlog(Blog blog);

    /**
     * 删除blog
     * @param id
     */
       void removeBlog(Long id);
    /**
     * 更新Blog
     * @param blog
     * @return
     */
    Blog updateBlog(Blog blog);

    /**
     * 根据ID 或者blog
     * @param id
     * @return
     */
      Blog getBlogById(Long id);
      /**
       * 根据用户进行博客名称 分页模糊查询（最新）
       */
      Page<Blog> listBlogsByTitleVote(User user, String title, Pageable pageable);
    /**
     * 根据用户进行博客名称 分页模糊查询（最热）
     */
    Page<Blog> listBlogsByTitleAndSort(User user, String title, Pageable pageable);

    /**
     * 阅读量递增
     * @param id
     */
    void readingIncrease(Long id);
    /**
     * 发表评论
     * @param blogId
     * @param commentContent
     * @return
     */
    Blog createComment(Long blogId, String commentContent);

    /**
     * 删除评论
     * @param blogId
     * @param commentId
     * @return
     */
    void removeComment(Long blogId, Long commentId);

    /**
     * 点赞
     * @param id
     * @return
     */
    Blog createVote(Long id);

    /**
     * 取消点赞
     * @param blogId
     * @param voteId
     */
   void removeVote(Long blogId,Long voteId);

    /**
     * 根据分类进行查询
     * @param catalog
     * @param pageable
     * @return
     */
   Page<Blog> listBlogsByCatalog(Catalog catalog,Pageable pageable);
}
