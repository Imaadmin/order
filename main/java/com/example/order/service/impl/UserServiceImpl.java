package com.example.order.service.impl;

import com.example.order.dao.DaoSupport;
import com.example.order.entities.User;
import com.example.order.entities.UserOrder;
import com.example.order.service.UserService;
import com.example.order.util.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Resource(name = "daoSupport")
    private DaoSupport dao;

    //获取讲师列表
    @Override
    public List<User> getUserList(PageData pd) throws Exception {
        pd.put("start", Integer.parseInt(pd.get("start").toString()));
        pd.put("length", Integer.parseInt(pd.get("length").toString()));
        pd.put("keyword", pd.get("search[value]").toString());
        return (List<User>) dao.findForList("UserMapper.getUserList", pd);
    }

    //获取用户详情
    @Override
    public User getUserInfoById(PageData pd) throws Exception {
        return  (User) dao.findForObject("UserMapper.getUserInfoById",pd);
    }

    //获取用户订单列表
    @Override
    public List<UserOrder> getUserOrderList(PageData pd) throws Exception {
        pd.put("start", Integer.parseInt(pd.get("start").toString()));
        pd.put("length", Integer.parseInt(pd.get("length").toString()));
        pd.put("keyword", pd.get("search[value]").toString());

        return (List<UserOrder>) dao.findForList("UserMapper.getUserOrderList", pd);
    }

    @Override
    public void updateUserOrderStatus(PageData pd) throws Exception {
        dao.update("UserMapper.updateUserOrderStatus", pd);
    }


}


