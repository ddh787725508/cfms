package com.acat.spring.boot.blog.repository;

import com.acat.spring.boot.blog.domain.Catalog;
import com.acat.spring.boot.blog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatalogRepository extends JpaRepository<Catalog,Long> {
    /**
     * 根据用户查询
     * @return
     */
    List<Catalog> findByUser(User user);

    /**
     * 根据用户分类和名称查询
     * @param user
     * @param name
     * @return
     */
    List<Catalog> findByUserAndName(User user,String name);
}
