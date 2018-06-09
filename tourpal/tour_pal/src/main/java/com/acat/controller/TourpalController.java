package com.acat.controller;

import com.acat.po.UserInfo;
import com.acat.po.ViewInfo;
import com.acat.service.UserInfoService;

import com.acat.service.ViewInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/tourpal/")
public class TourpalController {
    @Autowired
    private UserInfoService service;
    @Autowired
    private ViewInfoService viewService;
    @RequestMapping("reg.do")
      public String reg(UserInfo userInfo, Model model){
      if (userInfo!=null){
          userInfo.setUserType("普通用户");
      }
      boolean tag=service.add(userInfo);
      if (tag){
          return "/login";
      }else{
          return "/reg";
      }
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

            UserInfo user = service.getUser(userNumber);

            if(user!=null && user.getUserPw().equals(userPw)){
                session.setAttribute("userInfo", user);
                System.out.println(session.getId());
                return  query(model);
            }else{
                model.addAttribute("info", "账号或密码错误");
            }

        }else{
            model.addAttribute("info", "账号密码不能为空");
        }
        return "/login";
    }

    /**
     * 查询所有景区
     * @param model
     * @return
     */
   @RequestMapping("query.do")
    public String query(Model model){
        List<ViewInfo> list= viewService.getList(null);
        model.addAttribute("list",list);
        return "/index";
    }

    /**
     * 根据编号查询景区信息
     * @param viewId
     * @param model
     * @return
     */
    @RequestMapping("queryInfo.do")
    public String queryInfo(Integer viewId, Model model) {
        System.out.println("---------------------------------");
        System.out.println("viewId="+viewId);
        ViewInfo viewInfo = viewService.getInfo(viewId);

        model.addAttribute("viewinfo", viewInfo);

        return "/view_info";
    }
    @SuppressWarnings("all")
    @RequestMapping("logout.do")
    public void   logout(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException, ServletException {
        HttpSession session = request.getSession(false);//防止创建Session
        session.removeAttribute("userInfo");
        session.invalidate();
        System.out.println("logout="+session.getId());
        request.getRequestDispatcher("/login.jsp").forward(request, response);

    }
    @RequestMapping("updatePw.do")
    public String updatePw(UserInfo user, String passWord, Model model,  HttpSession session) {
                UserInfo userInfo=(UserInfo) session.getAttribute("userInfo");
                if (passWord!=null&&userInfo!=null&&passWord.equals(userInfo.getUserPw())){
                    boolean tag=service.update(user);
                    if(tag){
                        model.addAttribute("info", "密码修改成功，请重新登录");
                        return "login";
                    }
                }else{
                    model.addAttribute("info", "原密码输入不正确!");
                }

        return "/user_password";
    }

}
