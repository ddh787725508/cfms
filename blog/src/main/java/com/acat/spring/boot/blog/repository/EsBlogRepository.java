package com.acat.spring.boot.blog.repository;

import com.acat.spring.boot.blog.domain.elasticsearch.EsBlog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Blog 存储库.
 */
public interface EsBlogRepository extends ElasticsearchRepository<EsBlog,String> {
    /**
     * 模糊查询去重
     * @param title
     * @param summary
     * @param content
     * @param tags
     * @param pageable
     * @return
     */
     Page<EsBlog> findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContaining(String title, String summary,String content, String tags,Pageable pageable);

    /**
     * 根据Id进行查找
     * @param blogId
     * @return
     */
   EsBlog findByBlogId(Long blogId);
}
