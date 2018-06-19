package com.acat.spring.boot.blog.service;

import com.acat.spring.boot.blog.domain.Catalog;
import com.acat.spring.boot.blog.domain.User;

import java.util.List;

/**
 * Catalog Service服务接口
 */
public interface CatalogService {
    /**
     * 保存分类
     * @param catalog
     * @return
     */
   Catalog saveCatalog(Catalog catalog);

    /**
     * 删除分类
     * @param id
     */
   void removeCatalog(Long id);

    /**
     * 根据Id 获取分类
     * @param id
     * @return
     */
   Catalog getCatalogById(Long id);

    /**
     * 获取分类列表
     * @param user
     * @return
     */
   List<Catalog> listCatalogs(User user);
}
