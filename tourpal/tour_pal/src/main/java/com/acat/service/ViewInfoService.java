package com.acat.service;

import com.acat.po.ViewInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 景区管理的业务接口
 */

public interface ViewInfoService {
    /**
     * 添加景区
     * @param info
     * @return
     */
     boolean add(ViewInfo info);

    /**
     * 查询景区信息
     * @param info
     * @return
     */
    List<ViewInfo> getList(ViewInfo info);

    /**
     * 得到id得到景区的信息
     * @param viewId
     * @return
     */
    ViewInfo getInfo(Integer viewId);

    /**
     * 更新景区信息
     * @param info
     * @return
     */
    boolean update(ViewInfo info);

    /**
     * 删除景区信息
     * @param viewId
     * @return
     */
    boolean delete(Integer viewId);
}
