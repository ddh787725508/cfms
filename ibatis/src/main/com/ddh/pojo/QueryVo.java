package com.ddh.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ddh on 2018/5/11.
 */
public class QueryVo implements Serializable {
    private User user;

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    List<Integer> idList;
    Integer[] ids;
    private static final long serialVersionUID=2L;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
