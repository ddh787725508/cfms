package com.dharma.cfms.controller;

import com.dharma.cfms.domain.User;
import com.dharma.cfms.service.UserService;
import com.dharma.cfms.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 查询所有用户
     * @param pageIndex
     * @param pageSize
     * @param name
     * @param model
     * @return
     */
       @GetMapping
    public ModelAndView list(
               @RequestParam(value="async",required=false) boolean async,
               @RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
               @RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
               @RequestParam(value="name",required=false,defaultValue="") String name,
            Model model){
           /**
            * 根据起始页和请求页面的大小进行查询，然后将查询到的数据返回给前端
            */
           return new ModelAndView("/users/list","userModel",model);
       }

    /**
     * 根据Id删除相应的用户
     * @param id
     * @param model
     * @return
     */
       @DeleteMapping("/{id}")
       public ResponseEntity<Response> delete(@PathVariable("id") Long id,Model model){
           //删除，删除失败应该响应什么信息

           return ResponseEntity.ok().body(new Response(true,"处理成功")); //Response 是一个后台处理结果，不管成功还是失败都对客户端有一个响应
       }
       @GetMapping("/add")
       public ModelAndView createForm(Model model){
           //添加用户的逻辑代码
           return new ModelAndView("/users/add","UserModel",model);
       }
       @PostMapping
       public ResponseEntity<Response> create(User user,Model model){
           //1、首先判断改用户有没有注册过
           //2、没有注册的话，判单密码是否被修改过
           //3、进行相应信息的保存
           return ResponseEntity.ok().body(new Response(true,"注册成功",user));
       }
}
