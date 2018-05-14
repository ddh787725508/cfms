package com.ddh.test;



import com.ddh.mapper.OrdersMapper;
import com.ddh.mapper.UserMapper;
import com.ddh.pojo.Orders;
import com.ddh.pojo.QueryVo;
import com.ddh.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ddh on 2018/5/10.
 */
@SuppressWarnings("all")
public class testMapper {



    @Test
    public void testMapper() throws Exception {
        //加载核心配置文件
        String resource = "sqlMapConfig.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        //创建SqlSessionFactory
        SqlSessionFactory  sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
         SqlSession sqlSession=sqlSessionFactory.openSession();
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
        User user=userMapper.findUserById(10);
        System.out.println(user);
    }
    @Test
    public void testMapper1() throws Exception {
        //加载核心配置文件
        String resource = "sqlMapConfig.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        //创建SqlSessionFactory
        SqlSessionFactory  sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession=sqlSessionFactory.openSession();
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
        QueryVo vo=new QueryVo();
        User user=new User();
        user.setUsername("五");
        vo.setUser(user);
        List<User> users=userMapper.findUserByQueryVo(vo);
        System.out.println(users);

    }
    @Test
    public void testMapper2() throws Exception {
        //加载核心配置文件
        String resource = "sqlMapConfig.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        //创建SqlSessionFactory
        SqlSessionFactory  sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession=sqlSessionFactory.openSession();
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);

           int count= userMapper.countUser();
        System.out.println(count);

    }
    @Test
    public void testMapper3() throws Exception {
        //加载核心配置文件
        String resource = "sqlMapConfig.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        //创建SqlSessionFactory
        SqlSessionFactory  sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession=sqlSessionFactory.openSession();
      OrdersMapper ordersMapper=sqlSession.getMapper(OrdersMapper.class);

        List<Orders> list = ordersMapper.selectOrdersList();
        System.out.println(list);

    }
    @Test
    public void testMapper4() throws Exception {
        //加载核心配置文件
        String resource = "sqlMapConfig.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        //创建SqlSessionFactory
        SqlSessionFactory  sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession=sqlSessionFactory.openSession();
                 UserMapper userMapper= sqlSession.getMapper(UserMapper.class);
                User user=new User();
        user.setSex("1");
        user.setUsername("张小明");
        List<User> list = userMapper.selectUserBySexAndUsername(user);
        System.out.println(list);

    }
    @Test
    public void testMapper5() throws Exception {
        //加载核心配置文件
        String resource = "sqlMapConfig.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        //创建SqlSessionFactory
        SqlSessionFactory  sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession=sqlSessionFactory.openSession();
       UserMapper userMapper= sqlSession.getMapper(UserMapper.class);
        List list=new ArrayList();
        list.add(16);
        list.add(22);
        list.add(24);
//        QueryVo vo=new QueryVo();
//        vo.setIdList(list);
        Integer[] ids={22,16,24};
          List<User> users= userMapper.selectUserByIds(list);
        for (User user:users) {
            System.out.println(user);
        }
    }
    @Test
    public void testMapper6() throws Exception {
        //加载核心配置文件
        String resource = "sqlMapConfig.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        //创建SqlSessionFactory
        SqlSessionFactory  sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession=sqlSessionFactory.openSession();
        OrdersMapper ordersMapper= sqlSession.getMapper(OrdersMapper.class);
        List<Orders> orderses=ordersMapper.selectOrders();
        for (Orders orders:orderses) {
            System.out.println(orders);
        }
    }
    @Test
    public void testMapper7() throws Exception {
        //加载核心配置文件
        String resource = "sqlMapConfig.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        //创建SqlSessionFactory
        SqlSessionFactory  sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession=sqlSessionFactory.openSession();
        OrdersMapper ordersMapper= sqlSession.getMapper(OrdersMapper.class);
        List<User> users=ordersMapper.selectUserList();
        for (User user:users) {
            System.out.println(user);
        }
    }
}
