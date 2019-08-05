
package com.example.order.controller.common;

import com.example.order.controller.Base.BaseController;
import com.example.order.controller.Base.InterfaceResult;
import com.example.order.entities.User;
import com.example.order.entities.UserOrder;
import com.example.order.service.UserService;
import com.example.order.util.PageData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户管理
 *
 * @author 李磊
 * @version 1.0
 */
@RestController
public class UserController extends BaseController {

    @Resource
    UserService userserService;


    /**
     * 获取用户列表
     */
    @RequestMapping("/user/user/getUserList")
    public Object getUserList() throws Exception {
        PageData pd = this.getPageData();
        List<User> list = userserService.getUserList(pd);
        Long recordsTotal = list.size() == 0 ? 0l : list.get(0).getTotal();
        return InterfaceResult.returnTableSuccess(list, recordsTotal, pd.get("draw"));
    }


    /**
     * 根据id获取报名用户详情
     */
    @RequestMapping("/user/user/getUserInfoById")
    public Object getUserInfoById() throws Exception {
        PageData pd = this.getPageData();
        //根据id查找用户详情
        User user = userserService.getUserInfoById(pd);
        return InterfaceResult.returnSuccess(user);
    }


    /**
     * 根据id获取用户订单列表
     */
    @RequestMapping("/user/user/getUserOrderList")
    public Object getUserOrderList() throws Exception {
        PageData pd = this.getPageData();
        //根据id获取用户已报名课程
        List<UserOrder> list = userserService.getUserOrderList(pd);
        Long recordsTotal = list.size() == 0 ? 0l : list.get(0).getTotalPage();
        return InterfaceResult.returnTableSuccess(list, recordsTotal, pd.get("draw"));
    }


    /**
     * 修改用户订单状态 1未支付 2已支付未上餐 3已上餐
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/user/user/updateUserOrderStatus")
    public Object updateUserCourseStatus(@RequestParam(required = true) String oid) throws Exception {
        PageData pd = this.getPageData();
        pd.put("oid",oid);
        userserService.updateUserOrderStatus(pd);
        return InterfaceResult.returnSuccess(null);
    }

}
