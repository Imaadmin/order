package com.example.order.service;


import com.example.order.entities.Admin;
import com.example.order.util.PageData;

import javax.servlet.http.HttpServletRequest;


public interface AdminService {

    //通过用户名查找管理员
    Admin findAdminByUsername(String username) throws Exception;

    //修改密码
    void updateAdminPassword(PageData pd, HttpServletRequest request) throws Exception;
}
