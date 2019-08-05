package com.example.order.service;


import com.example.order.entities.User;
import com.example.order.entities.Variety;
import com.example.order.util.PageData;

import java.util.List;


public interface VarietyService {

    //获取菜品列表
    List<Variety> getVarietyList(PageData pd) throws Exception;

    //新增收费信息
    void addVariety(PageData pd) throws Exception;

}
