package com.acat.spring.boot.blog.controller;

import com.acat.spring.boot.blog.domain.Authority;
import com.acat.spring.boot.blog.domain.User;
import com.acat.spring.boot.blog.service.AuthorityService;
import com.acat.spring.boot.blog.service.UserService;
import com.acat.spring.boot.blog.util.ConstraintViolationExceptionHandler;
import com.acat.spring.boot.blog.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")  // 指定角色权限才能操作方法

public class UserController {

    @Autowired
     private UserService userService;
    @Autowired
    private AuthorityService authorityService;
    /**
     * 查询所有用户
     * @param model
     * @return
     */
    @GetMapping
    public ModelAndView list(@RequestParam(value="async",required=false) boolean async,
                             @RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
                             @RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
                             @RequestParam(value="name",required=false,defaultValue="") String name,
                             Model model) {
        Pageable pageable = new PageRequest(pageIndex, pageSize);
        Page<User> page = userService.listUsersByNameLike(name, pageable);
        System.out.println("page="+page.getContent());
        List<User> list = page.getContent();	// 当前所在页面数据列表
        System.out.println("page="+list.size());
        model.addAttribute("page", page);
        model.addAttribute("userList", list);
        return new ModelAndView(async==true?"users/list :: #mainContainerRepleace":"users/list", "userModel", model);


    }


    /**
     * 删除id
     * @param id
     * @param model
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id, Model model){
        try{
            userService.removeUser(id);
        }catch (Exception e){
            return ResponseEntity.ok().body(new Response(false,e.getMessage()));
        }
        return ResponseEntity.ok().body(new Response(true,"处理成功"));
    }
    /**
     * 获取 form 表单页面
     * @param
     * @return
     */
    @GetMapping("/add")
    public ModelAndView createForm(Model model) {
        model.addAttribute("user", new User(null,null,null,null));

        return new ModelAndView("users/add", "userModel", model);
    }

@GetMapping("/edit/{id}")
    public ModelAndView modifyForm(@PathVariable("id") Long id, Model model){
       User user=userService.getUserById(id);
       model.addAttribute("user",user);
       return new ModelAndView("users/edit","userModel",model);
    }

    /**
     * 创建用户
     * @param user
     * @param authorityId
     * @return
     */
    @PostMapping
    public ResponseEntity<Response> create(User user, Long authorityId) {
           List<Authority> authorities=new ArrayList<>();
           authorities.add(authorityService.getAuthorityById(authorityId));
           user.setAuthorities(authorities);
        System.out.println("userpass="+user.getPassword());
           if (user.getId()==null){
               System.out.println("111111111111111111111111111111111111111111111111");
               user.setEncodePassword(user.getPassword());
           }else {
               User originUser=userService.getUserById(user.getId());
               String rawPassword=originUser.getPassword();
               PasswordEncoder encoder=new BCryptPasswordEncoder();
               String encodePassword=encoder.encode(user.getPassword());
                boolean isMatch= encoder.matches(rawPassword,encodePassword);
                if (!isMatch){
                    user.setEncodePassword(user.getPassword());
                }else {
                    user.setEncodePassword(user.getPassword());
                }
           }
           try{
               userService.saveOrUpdateUser(user);
           }catch (ConstraintViolationException e){
                return  ResponseEntity.ok().body(new Response(false,ConstraintViolationExceptionHandler.getMessage(e)));
           }
        return ResponseEntity.ok().body(new Response(true, "处理成功", user));
    }

}
