package com.acat.controller;

import com.acat.po.UserInfo;
import com.acat.po.ViewInfo;
import com.acat.service.ViewInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/viewinfo/")
public class ViewInfoController {
    @Autowired
    private ViewInfoService service;
    /**
     * 添加景区
     * @param file
     * @param viewinfo
     * @param model
     * @return
     */
  @RequestMapping("add.do")
  public String add(@RequestParam(value = "file", required = false) MultipartFile file,
          ViewInfo viewinfo, Model model, HttpSession session) {

      // 上传地址
      String path = session.getServletContext().getRealPath("upload");
      System.out.println(path);

      String filename = UUID.randomUUID() + file.getOriginalFilename();
      System.out.println(filename);
      File targetFile = new File(path, filename);
      if (!targetFile.exists()) {
          targetFile.mkdirs();
      }

      UserInfo user = (UserInfo) session.getAttribute("userInfo");
      if (viewinfo != null) {
          viewinfo.setUserNumber(user.getUserNumber());
          viewinfo.setViewTime(new Date());
          viewinfo.setViewPhoto(filename);
      }

      try {
          file.transferTo(targetFile);
          boolean mark = service.add(viewinfo);
          if (mark) {
              model.addAttribute("info", "添加景区成功");
          } else {
              model.addAttribute("info", "添加景区失败");
          }
      } catch (Exception e) {
          e.printStackTrace();
          model.addAttribute("info", "添加景区失败");
      }

      System.out.println(viewinfo);

      return "/jsp/admin/viewinfo/viewinfo_info";
  }

    /**
     * 查询景区信息
     * @param info
     * @param model
     * @return
     */
  @RequestMapping("list.do")
    public String list(ViewInfo info,Model model){
      List<ViewInfo> list=service.getList(info);
      model.addAttribute("list",list);
     return "/jsp/admin/viewinfo/viewinfo_list";
  }

    /**
     * 加载修改页面
     * @param viewId
     * @param model
     * @return
     */
    @RequestMapping("load.do")
  public String load(Integer viewId ,Model model){
      model.addAttribute("viewinfo",service.getInfo(viewId));
      return "/jsp/admin/viewinfo/viewinfo_update";
  }

    /**
     * 更新景区
     * @param file
     * @param viewinfo
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("update.do")
    public String update(
            @RequestParam(value = "file", required = false) MultipartFile file,
            ViewInfo viewinfo, Model model, HttpSession session) {
            String path=session.getServletContext().getRealPath("upload");
             System.out.println(path);
            String filename = UUID.randomUUID() + file.getOriginalFilename();
            System.out.println(filename);
            System.out.println(file.getOriginalFilename());
            if (viewinfo!=null&&file!=null&&file.getOriginalFilename()!=null&&!file.getOriginalFilename().equals("")){
                viewinfo.setViewPhoto(filename);
                File targetFile=new File(path,filename);
                if (!targetFile.exists()){
                    targetFile.mkdirs();
                }
                try {
                    file.transferTo(targetFile);

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
            boolean tag=service.update(viewinfo);
        if (tag) {
            model.addAttribute("info", "修改景区成功");
        } else {
            model.addAttribute("info", "修改景区失败");
        }
        return "/jsp/admin/viewinfo/viewinfo_info";
    }
   @RequestMapping("delete.do")
     public String delete(Integer viewId,Model model){
        boolean tag=service.delete(viewId);
         if (tag) {
             model.addAttribute("info", "删除景区成功");
         } else {
             model.addAttribute("info", "删除景区失败");
         }
         return "/jsp/admin/viewinfo/viewinfo_info";
     }
}
