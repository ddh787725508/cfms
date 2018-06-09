package com.acat.mapper;

import com.acat.po.ViewInfo;

import java.util.List;


public interface ViewInfoMapper {
    int deleteByPrimaryKey(Integer viewId);

    int insert(ViewInfo record);

    int insertSelective(ViewInfo record);

    ViewInfo selectByPrimaryKey(Integer viewId);

    int updateByPrimaryKeySelective(ViewInfo record);

    int updateByPrimaryKey(ViewInfo record);
    
    public List<ViewInfo> getViewInfoList(ViewInfo record);
}