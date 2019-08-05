package com.example.order.service.impl;

import com.example.order.dao.DaoSupport;
import com.example.order.entities.Category;
import com.example.order.service.CategoriesService;
import com.example.order.util.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Resource(name = "daoSupport")
    private DaoSupport dao;


    //查询所有分类
    @Override
    public List<Category> getCategoriesList(PageData pd) throws Exception {
        pd.put("start", Integer.parseInt(pd.get("start").toString()));
        pd.put("length", Integer.parseInt(pd.get("length").toString()));
        pd.put("keyword", pd.get("search[value]").toString());

        return (List<Category>) dao.findForList("CategoriesMapper.getCategoriesList", pd);
    }


    //删除分类
    @Override
    public void deleteCategory(PageData pd) throws Exception {
        dao.delete("CategoriesMapper.deleteCategory", pd);
    }


    //获取分类列表
    @Override
    public List<Category> getAllCategories(PageData pd) throws Exception {
        return (List<Category>) dao.findForList("CategoriesMapper.getAllcategories", pd);
    }


    //新增分类
    @Override
    public void addCategory(PageData pd) throws Exception {
        Date now = new Date();
        pd.put("create_time", now);
        dao.save("CategoriesMapper.addCategory", pd);
    }
}


