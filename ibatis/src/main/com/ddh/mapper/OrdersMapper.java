package com.ddh.mapper;

import com.ddh.pojo.Orders;
import com.ddh.pojo.QueryVo;
import com.ddh.pojo.User;

import java.util.List;

/**
 * Created by ddh on 2018/5/11.
 */
public interface OrdersMapper {
    /**
     *
     * @param
     * @return
     * 遵循四个原则
     * 1、接口方法名与mapper.xml中的id值相同
     * 返回值类型与mapper.xml中的返回值类型相同
     * 方法中的参数与Mapper.xml中的参数类型一致
     * 命名空间绑定接口
     */
     public List<Orders> selectOrdersList();
     public List<Orders> selectOrders();
     public List<User> selectUserList();

}
