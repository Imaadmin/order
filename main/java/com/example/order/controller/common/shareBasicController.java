package com.example.order.controller.common;

import com.example.order.util.PageData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class shareBasicController {

    public PageData getPageData() {
        return new PageData(this.getRequest());
    }

    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }


    /**
     * 跳转欢迎页面
     */
    @RequestMapping(value = "/shareBasicController/toWelcomePage")
    public String toWelcomePage() {
        return "welcome";
    }


    /**
     * 跳转到用户管理页面
     */
    @RequestMapping(value = "/user/toUserPage")
    public String toUserPage(Model model){
        PageData pd = this.getPageData();

        model.addAttribute("id", pd);
        return "user/user";
    }


    /**
     * 跳转到用户订单详情页面
     */
    @RequestMapping(value = "/user/toUserInfoPage")
    public String toUserInfoPage(@RequestParam(required = true) int id, Model model){
        model.addAttribute("id", id);
        return "user/userInfo";
    }


    /**
     * 跳转到分类管理页面
     */
    @RequestMapping(value = "/categories/toCategoriesPage")
    public String toCategoriesPage() {
        return "categories/categories";
    }


    /**
     * 跳转到菜品管理页面
     */
    @RequestMapping(value = "/variety/toVarietyPage")
    public String toCourseInfo(){
        return "variety/variety";
    }


    /**
     * 跳转到订单管理页面
     */
    @RequestMapping(value = "/orders/toOrdersPage")
    public String toOrdersPage() throws Exception {
        return "orders/orders";
    }


    /**
     * 跳转到订单管理详情页面
     */
    @RequestMapping(value = "/orders/toOrderInfoPage")
    public String toOrderInfoPage(@RequestParam(required = true) String oid, Model model) {
        model.addAttribute("oid", oid);
        return "orders/orderInfo";
    }


    /**
     * 跳转平台首页（数据统计）
     */
    @RequestMapping(value = "/home/toHomePage")
    public String toHomePage() {
        return "home/home";
    }


    /**
     * 跳转banner页面
     */
    @RequestMapping(value = "/banner/toBannerPage")
    public String toBannerPage() {
        return "banner/banner";
    }

}
