package com.example.order.controller;

import com.example.order.controller.Base.BaseController;
import com.example.order.controller.Base.InterfaceResult;
import com.example.order.entities.Admin;
import com.example.order.service.AdminService;
import com.example.order.util.PageData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Map;

@Controller
public class LoginController extends BaseController {
    @Resource
    AdminService adminService;

    @RequestMapping(value = "/tologin")
    public String tologin() throws Exception {

        return "login";
    }

    //@RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session) throws Exception {
        Admin admin = adminService.findAdminByUsername(username);
        if (admin != null && password.equals(admin.getPassword())){
            //登陆成功，防止表单重复提交，可以重定向到主页
            session.setAttribute("admin", admin);
            return "redirect:/main.html";
        } else {
            //登陆失败

            map.put("msg", "用户名密码错误");
            return "login";
        }

    }

    /**
     * 修改密码
     */
    @ResponseBody
    @RequestMapping(value = "/shareChangePassword.do")
    public Object hrsscChangePassword(@RequestParam(required = true) String oldPassword,
                                      @RequestParam(required = true) String newPassword, HttpServletRequest request) throws Exception {
        PageData pd = this.getPageData();
        adminService.updateAdminPassword(pd, request);
        return InterfaceResult.returnSuccess(null);
    }

    /**
     * 退出登录
     *
     * @param request
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ResponseBody
    @RequestMapping(value = "/sharecLoginOut.do")
    public Object hrsscLoginOut(HttpServletRequest request) {
        // 清空session
        HttpSession session = request.getSession();
        Enumeration em = request.getSession().getAttributeNames();
        while (em.hasMoreElements()) {
            session.removeAttribute(em.nextElement().toString());
        }
        return InterfaceResult.returnSuccess(null);
    }
}
