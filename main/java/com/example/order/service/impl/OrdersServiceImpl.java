package com.example.order.service.impl;

import com.example.order.dao.DaoSupport;
import com.example.order.entities.OrdersInfo;
import com.example.order.entities.UserOrder;
import com.example.order.service.OrsersService;
import com.example.order.util.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class OrdersServiceImpl implements OrsersService {

    @Resource(name = "daoSupport")
    private DaoSupport dao;


    //查询所有报名课程
    @Override
    public List<UserOrder> getOrderList(PageData pd) throws Exception {
        pd.put("start", Integer.parseInt(pd.get("start").toString()));
        pd.put("length", Integer.parseInt(pd.get("length").toString()));
        pd.put("keyword", pd.get("search[value]").toString());

        return (List<UserOrder>) dao.findForList("OrdersMapper.getOrderList", pd);
    }

    //获取课程分类列表
    @Override
    public List<OrdersInfo> getOrderInfoList(PageData pd) throws Exception {
        pd.put("start", Integer.parseInt(pd.get("start").toString()));
        pd.put("length", Integer.parseInt(pd.get("length").toString()));
        return (List<OrdersInfo>)dao.findForList("OrdersMapper.getOrderInfoList", pd);
    }

}


