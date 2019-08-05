package com.example.order.service.impl;

import com.example.order.dao.DaoSupport;
import com.example.order.entities.Admin;
import com.example.order.service.AdminService;
import com.example.order.util.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Service
public class AdminServiceImpl implements AdminService {

    @Resource(name = "daoSupport")
    private DaoSupport dao;


    @Override
    public Admin findAdminByUsername(String username) throws Exception {
        return (Admin) dao.findForObject("AdminMapper.findAdminByUsername", username);

    }

    @Override
    public void updateAdminPassword(PageData pd, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        pd.put("uid", admin.getId());
        dao.update("AdminMapper.updatePwd", pd);
    }
}


