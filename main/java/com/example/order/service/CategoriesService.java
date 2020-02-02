package com.example.order.service;


import com.example.order.entities.Category;
import com.example.order.util.PageData;

import java.util.List;


public interface CategoriesService {
    //获取分类列表
    List<Category> getCategoriesList(PageData pd) throws Exception;

    //更具id删除分类
    void deleteCategory(PageData pd) throws Exception;

    //获得所有课程分类
    List<Category> getAllCategories(PageData pd) throws Exception;

    //新增父分类
    void addCategory(PageData pd) throws Exception;

    //查找子分类
    List<Category> selectChildren(PageData pd) throws Exception;

    //获取所以父分类
    List<Category> getAllFatherCategories(PageData pd) throws Exception;

    //新增子分类
    void addSunCategory(PageData pd) throws Exception;
}
