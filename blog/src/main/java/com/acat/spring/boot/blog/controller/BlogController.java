package com.acat.spring.boot.blog.controller;

import com.acat.spring.boot.blog.domain.User;
import com.acat.spring.boot.blog.domain.elasticsearch.EsBlog;
import com.acat.spring.boot.blog.service.EsBlogService;
import com.acat.spring.boot.blog.vo.TagVO;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * blog 控制器
 */
@Controller
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private EsBlogService esBlogService;
    @GetMapping
    public String listEsBlogs(
            @RequestParam(value="order",required=false,defaultValue="new") String order,
            @RequestParam(value="keyword",required=false,defaultValue="" ) String keyword, //
            @RequestParam(value="async",required=false) boolean async,
            @RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
            @RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
            Model model) {
        List<EsBlog> list =null;
        Page<EsBlog> page = null;

        boolean isEmpty = true; // 系统初始化时，没有博客数据
        try {
            if (order.equals("hot")) { // 最热查询
                Sort sort = new Sort(Sort.Direction.DESC,"readSize","commentSize","voteSize","createTime");
                Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
                page = esBlogService.listHotestEsBlogs(keyword, pageable);
            } else if (order.equals("new")) { // 最新查询
                Sort sort = new Sort(Sort.Direction.DESC,"createTime");
                Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
                page = esBlogService.listNewestEsBlogs(keyword, pageable);
            }

            isEmpty = false;
        } catch (Exception e) {
            Pageable pageable = new PageRequest(pageIndex, pageSize);
            page = esBlogService.listEsBlogs(pageable);
        }

        list = page.getContent();	// 当前所在页面数据列表


        model.addAttribute("order", order);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("blogList", list);
        System.out.println("--------------------------------------");
        System.out.println("order="+order);
        System.out.println("keyword="+keyword);
        System.out.println("------------------------");
        // 首次访问页面才加载
        System.out.println("isEmpty="+isEmpty);
        System.out.println("ssync="+async);
        if (!async && !isEmpty) {
            List<EsBlog> newest = esBlogService.listTop5NewestEsBlogs();
            System.out.println("11111111111111111111111111111111111111111111111111");
            model.addAttribute("newest", newest);
            List<EsBlog> hotest = esBlogService.listTop5HotestEsBlogs();
            model.addAttribute("hotest", hotest);
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//            List<TagVO> tags = esBlogService.listTop30Tags();
//            System.out.println("size="+tags.size());
//            model.addAttribute("tags", tags);
//            System.out.println("tags="+tags);
//            List<User> users = esBlogService.listTop12Users();
//            System.out.println("users.size="+users.size());
//            model.addAttribute("users", users);
        }

        System.out.println("order="+order);
        return (async==true?"index :: #mainContainerRepleace":"index");
//    return "index";
    }

    @GetMapping("/newest")
    public String listNewestEsBlogs(Model model) {
        List<EsBlog> newest = esBlogService.listTop5NewestEsBlogs();
        model.addAttribute("newest", newest);
        return "newest";
    }

    @GetMapping("/hotest")
    public String listHotestEsBlogs(Model model) {
        List<EsBlog> hotest = esBlogService.listTop5HotestEsBlogs();
        model.addAttribute("hotest", hotest);
        return "hotest";
    }
}
