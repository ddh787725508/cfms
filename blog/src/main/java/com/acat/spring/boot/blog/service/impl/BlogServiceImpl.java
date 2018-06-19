package com.acat.spring.boot.blog.service.impl;

import com.acat.spring.boot.blog.domain.*;
import com.acat.spring.boot.blog.domain.elasticsearch.EsBlog;
import com.acat.spring.boot.blog.repository.BlogRepository;
import com.acat.spring.boot.blog.service.BlogService;
import com.acat.spring.boot.blog.service.EsBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private EsBlogService esBlogService;
    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        boolean isNew = (blog.getId() == null);
        EsBlog esBlog = null;

        Blog returnBlog = blogRepository.save(blog);

        if (isNew) {
            esBlog = new EsBlog(returnBlog);
        } else {
            esBlog = esBlogService.getEsBlogByBlogId(blog.getId());
            esBlog.update(returnBlog);
        }

        esBlogService.updateEsBlog(esBlog);
        return returnBlog;
    }
     @Transactional
    @Override
    public void removeBlog(Long id) {
         blogRepository.deleteById(id);
         EsBlog esBlog=esBlogService.getEsBlogByBlogId(id);
        esBlogService.removeEsBlog(esBlog.getId());
    }
       @Transactional
    @Override
    public Blog updateBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogRepository.getOne(id);
    }

    @Override
    public Page<Blog> listBlogsByTitleVote(User user, String title, Pageable pageable) {
         title="%"+title+"%";
         String tags=title;
        return blogRepository.findByUserAndTitleLikeOrderByCreateTimeDesc(user,tags,pageable);
    }

    @Override
    public Page<Blog> listBlogsByTitleAndSort(User user, String title, Pageable pageable) {
        title="%"+title+"%";
        return blogRepository.findByUserAndTitleLike(user,title,pageable);
    }

    @Override
    public void readingIncrease(Long id) {
      Blog blog=blogRepository.getOne(id);
      blog.setReadSize(blog.getCommentSize()+1);
      this.saveBlog(blog);
    }

    @Override
    public Blog createComment(Long blogId, String commentContent) {
       Blog origin=blogRepository.findById(blogId).get();
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment comment=new Comment(user,commentContent);
        origin.addComment(comment);
        return blogRepository.save(origin);
    }

    @Override
    public void removeComment(Long blogId, Long commentId) {
          Blog orginal=blogRepository.findById(blogId).get();
          orginal.removeComment(commentId);
          blogRepository.save(orginal);
    }

    @Override
    public Blog createVote(Long id) {
           Blog blog= blogRepository.findById(id).get();
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Vote vote=new Vote(user);
        boolean isExist=blog.addVote(vote);
        if (isExist){
            throw  new IllegalArgumentException("该用户已经点过赞了");
        }
        return this.saveBlog(blog);
    }

    @Override
    public void removeVote(Long blogId, Long voteId) {
        Blog blog= blogRepository.findById(blogId).get();
        blog.removeVote(voteId);
        this.saveBlog(blog);

    }

    @Override
    public Page<Blog> listBlogsByCatalog(Catalog catalog, Pageable pageable) {
        Page<Blog> blogs = blogRepository.findByCatalog(catalog, pageable);
        return blogs;
    }

}
