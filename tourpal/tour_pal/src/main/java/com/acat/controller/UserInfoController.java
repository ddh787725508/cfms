package com.acat.controller;


import com.acat.po.UserInfo;
import com.acat.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * 添加用户
 */
@Controller
@RequestMapping("/userinfo/")
public class UserInfoController {

@Autowired
private UserInfoService userInfoService;
    /**
     * 添加
     * @param userInfo
     * @param model
     * @return
     */
    @RequestMapping("add.do")
    public String add(UserInfo userInfo, Model model){
        try {
            userInfoService.add(userInfo);
            model.addAttribute("info", "添加成功");
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("info","添加失败");
        }
        System.out.println(userInfo);
        return "/jsp/admin/userinfo/userinfo_info";
    }

    /**
     * 查询用户信息
     * @param userInfo
     * @param model
     * @return
     */
    @RequestMapping("list.do")
     public String list(UserInfo userInfo,Model model){
        List<UserInfo> list=userInfoService.getUserList(userInfo);
        model.addAttribute("list",list);
        return "/jsp/admin/userinfo/userinfo_list";
     }

    @RequestMapping("update.do")
     public String update(UserInfo userInfo,Model model){
        boolean tag=false;
        try {
            tag=userInfoService.update(userInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (tag){
            model.addAttribute("info","更新成功");
        }else {
            model.addAttribute("info","更新失败");
        }
         return "/jsp/admin/userinfo/userinfo_info";
     }
    @RequestMapping("load.do")
    public String load(String userNumber ,Model model){
        UserInfo userInfo=userInfoService.getUser(userNumber);
          model.addAttribute("user",userInfo);
        return "/jsp/admin/userinfo/userinfo_update";
    }
    /**
     * 删除用户
     * @param userNumber
     * @param model
     * @return
     */
    @RequestMapping("delete.do")
    public String delete(String userNumber, Model model) {
        boolean tag = false;
        try {
            tag = userInfoService.delete(userNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (tag) {
            model.addAttribute("info", "删除成功");
        } else {
            model.addAttribute("info", "删除失败");
        }

        return "/jsp/admin/userinfo/userinfo_info";
    }

    /**
     * 用户登录
     * @param userNumber
     * @param userPw
     * @param model
     * @param
     * @return
     */
    @RequestMapping("login.do")
    public String login(String userNumber,String userPw, Model model,HttpSession session){

        if(userNumber!=null&&!userNumber.equals("")){

            UserInfo user = userInfoService.getUser(userNumber);

            if(user!=null && user.getUserPw().equals(userPw)&&"管理员".equals(user.getUserType())){
               session.setAttribute("userInfo", user);
                System.out.println(session.getId());
                return "/jsp/admin/index";
            }else{
                model.addAttribute("info", "账号或密码错误");
            }

        }else{
            model.addAttribute("info", "账号密码不能为空");
        }
        return "/jsp/admin/login";
    }
    @RequestMapping("logout.do")
      public void   logout(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException, ServletException {
        HttpSession session = request.getSession(false);//防止创建Session
         session.removeAttribute("userInfo");
         session.invalidate();
          System.out.println("logout="+session.getId());
             request.getRequestDispatcher("/jsp/admin/login.jsp").forward(request, response);

        }
}
