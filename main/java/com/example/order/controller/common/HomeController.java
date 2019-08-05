package com.example.order.controller.common;

import com.example.order.controller.Base.BaseController;
import com.example.order.controller.Base.InterfaceResult;
import com.example.order.entities.Home;
import com.example.order.service.HomeService;
import com.example.order.util.PageData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * 平台首页管理控制层
 */
@RestController
public class HomeController extends BaseController {

	@Resource
	HomeService homeService;


	/**
	 * 获取平台头部数据信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/home/getHomeData")
	public Object getHomeData() throws Exception {
		
		Home home = homeService.getHomeData();
		
		return InterfaceResult.returnSuccess(home);
	}


	/**
	 * 本周每日用户数量
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/home/getEverydayUser")
	public Object getEverydayUser() throws Exception {
		PageData pd = new PageData();
		
		List<Map<String,Object>> userEverydayCount = homeService.getEverydayUser(pd);
	
		return InterfaceResult.returnSuccess(userEverydayCount);
	}


	/**
	 * 本周每日订单数量
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/home/getEverydayOrder")
	public Object getEverydayOrder() throws Exception {
		PageData pd = new PageData();
		
		List<Map<String,Object>> orderEverydayCount = homeService.getEverydayOrder(pd);
	
		return InterfaceResult.returnSuccess(orderEverydayCount);
	}
}


