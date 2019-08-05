package com.example.order.service;


import com.example.order.entities.User;
import com.example.order.entities.UserOrder;
import com.example.order.util.PageData;

import java.util.List;


public interface UserService {

    //获取讲师列表
    List<User> getUserList(PageData pd) throws Exception;

    //获取用户详情
    User getUserInfoById(PageData pd) throws Exception;

    //获取用户订单列表
    List<UserOrder> getUserOrderList(PageData pd) throws Exception;

    //更新用户已报名课程
    void updateUserOrderStatus(PageData pd) throws Exception;
}
