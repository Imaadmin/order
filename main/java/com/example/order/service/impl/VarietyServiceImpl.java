package com.example.order.service.impl;

import com.example.order.dao.DaoSupport;
import com.example.order.entities.Variety;
import com.example.order.service.VarietyService;
import com.example.order.util.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
public class VarietyServiceImpl implements VarietyService {

    @Resource(name = "daoSupport")
    private DaoSupport dao;

    //获取菜品列表
    @Override
    public List<Variety> getVarietyList(PageData pd) throws Exception {
        pd.put("start", Integer.parseInt(pd.get("start").toString()));
        pd.put("length", Integer.parseInt(pd.get("length").toString()));
        pd.put("keyword", pd.get("search[value]").toString());

        return (List<Variety>) dao.findForList("VarietyMapper.getVarietyList", pd);
    }

    @Override
    public void addVariety(PageData pd) throws Exception {
        Date now = new Date();
        pd.put("create_time", now);
        dao.save("VarietyMapper.addVariety", pd);
    }


}


