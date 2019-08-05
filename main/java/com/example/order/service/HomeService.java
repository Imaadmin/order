package com.example.order.service;


import com.example.order.entities.Home;
import com.example.order.util.PageData;

import java.util.List;
import java.util.Map;


public interface HomeService {
	

	/**
	 * 获取平台头部数据信息 
	 * @author ZhouXiaobing 
	 * @date 2018年3月28日 下午2:49:52 
	 * @return
	 * @throws Exception
	 */
	Home getHomeData() throws Exception;
	
	
	/**
	 * 本周每日用户数量
	 * @author ZhouXiaobing 
	 * @date 2018年3月28日 下午3:58:32 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> getEverydayUser(PageData pd) throws Exception;
	

	/**
	 * 本周每日订单数量
	 * @author ZhouXiaobing 
	 * @date 2018年3月28日 下午4:01:39 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> getEverydayOrder(PageData pd) throws Exception;
	
}
