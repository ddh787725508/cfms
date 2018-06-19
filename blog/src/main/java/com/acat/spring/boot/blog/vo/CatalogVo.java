package com.acat.spring.boot.blog.vo;

import com.acat.spring.boot.blog.domain.Catalog;

import java.io.Serializable;

public class CatalogVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private Catalog catalog;

    public CatalogVo() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }
}
