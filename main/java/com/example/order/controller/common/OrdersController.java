
package com.example.order.controller.common;

import com.example.order.controller.Base.BaseController;
import com.example.order.controller.Base.InterfaceResult;
import com.example.order.entities.OrdersInfo;
import com.example.order.entities.UserOrder;
import com.example.order.service.OrsersService;
import com.example.order.util.PageData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * 订单管理
 * @author 李磊
 * @version 1.0
 */
@RestController
public class OrdersController extends BaseController {

    @Resource
	OrsersService orsersService;


	/**
	 *获取订单列表
	 */
	@RequestMapping("/orders/getOrderList")
	public Object getOrderList() throws Exception {
		PageData pd = this.getPageData();
		List<UserOrder> list = orsersService.getOrderList(pd);
		Long recordsTotal = list.size() == 0 ? 0l:list.get(0).getTotalPage();
		return InterfaceResult.returnTableSuccess(list, recordsTotal, pd.get("draw"));
	}


	/**
	 *获取订单详情列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/orders/getOrderInfoList")
	public Object getOrderInfoList() throws Exception {
		PageData pd = this.getPageData();
		List<OrdersInfo> ordersInfoList = orsersService.getOrderInfoList(pd);
		Long recordsTotal = ordersInfoList.size() == 0 ? 0l:ordersInfoList.get(0).getTotal();
		return InterfaceResult.returnTableSuccess(ordersInfoList, recordsTotal, pd.get("draw"));
	}


}
