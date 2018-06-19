package com.acat.spring.boot.blog.service.impl;

import com.acat.spring.boot.blog.domain.Catalog;
import com.acat.spring.boot.blog.domain.User;
import com.acat.spring.boot.blog.repository.CatalogRepository;
import com.acat.spring.boot.blog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * catalog service 实现
 */
@Service
public class CatalogServiceImpl implements CatalogService {
   @Autowired
    private CatalogRepository catalogRepository;
    @Override
    public Catalog saveCatalog(Catalog catalog) {
        //判断是否重复
        List<Catalog> list=catalogRepository.findByUserAndName(catalog.getUser(),catalog.getName());
        if (list!= null&&list.size()>0){
            throw  new IllegalArgumentException("该分类已经存在");
        }
        return catalogRepository.save(catalog) ;
    }

    @Override
    public void removeCatalog(Long id) {
        catalogRepository.deleteById(id);
    }

    @Override
    public Catalog getCatalogById(Long id) {
        return catalogRepository.findById(id).get();
    }

    @Override
    public List<Catalog> listCatalogs(User user) {
        return catalogRepository.findByUser(user);
    }
}
