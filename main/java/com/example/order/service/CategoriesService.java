package com.example.order.service;


import com.example.order.entities.Category;
import com.example.order.util.PageData;

import java.util.List;


public interface CategoriesService {
    //获取报名课程列表
    List<Category> getCategoriesList(PageData pd) throws Exception;

    //更具id删除课程
    void deleteCategory(PageData pd) throws Exception;

    //获得所有课程分类
    List<Category> getAllCategories(PageData pd) throws Exception;

    //新增报名课程
    void addCategory(PageData pd) throws Exception;
}
