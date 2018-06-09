package com.acat.service.impl;

import com.acat.mapper.ViewInfoMapper;
import com.acat.po.ViewInfo;
import com.acat.service.ViewInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewInfoServiceImpl implements ViewInfoService {
    @Autowired
    private ViewInfoMapper viewInfoMapper;
    public boolean add(ViewInfo info) {
        int i = viewInfoMapper.insertSelective(info);
        if (i > 0) {
            return true;
        }

        return false;
    }

    public List<ViewInfo> getList(ViewInfo info) {
        if (info!=null&&info.getViewName()!=null&&!info.getViewName().equals("")){
            info.setViewName("%"+info.getViewName()+"%");
        }
           return viewInfoMapper.getViewInfoList(info);
    }

    public ViewInfo getInfo(Integer viewId) {
        return viewInfoMapper.selectByPrimaryKey(viewId);
    }

    public boolean update(ViewInfo info) {
         int i=viewInfoMapper.updateByPrimaryKeySelective(info);
         if (i>0) return true;
         else return false;
    }

    public boolean delete(Integer viewId) {
      int i=viewInfoMapper.deleteByPrimaryKey(viewId);
        if (i > 0) return true;
        else return false;
    }
}
