package com.example.order.service;


import com.example.order.entities.OrdersInfo;
import com.example.order.entities.UserOrder;
import com.example.order.util.PageData;

import java.util.List;


public interface OrsersService {
    //获取报名课程列表
    List<UserOrder> getOrderList(PageData pd) throws Exception;

    //获得所有课程分类
    List<OrdersInfo> getOrderInfoList(PageData pd) throws Exception;

}
