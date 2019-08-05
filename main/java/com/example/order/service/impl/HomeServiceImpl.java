package com.example.order.service.impl;


import com.example.order.dao.DaoSupport;
import com.example.order.entities.Home;
import com.example.order.service.HomeService;
import com.example.order.util.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class HomeServiceImpl implements HomeService {


	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	//获取平台头部数据信息 
	@Override
	public Home getHomeData() throws Exception {
		return (Home) dao.findForObject("HomeMapper.getHomeData",null);
	}


	//本周每日用户数量
	@Override
	public List<Map<String, Object>> getEverydayUser(PageData pd) throws Exception {
		
		//获取当前时间
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();
		
		String year = sf.format(date).substring(0,4);
		String month = sf.format(date).substring(5,7);
		
		pd.put("year", year);
		pd.put("month", month);
		
		return (List<Map<String, Object>>) dao.findForList("HomeMapper.getEverydayUser",pd);
	}


	//本周每日订单数量
	@Override
	public List<Map<String, Object>> getEverydayOrder(PageData pd) throws Exception {
		
		//获取当前时间
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();
		
		String year = sf.format(date).substring(0,4);
		String month = sf.format(date).substring(5,7);
		
		pd.put("year", year);
		pd.put("month", month);
				
		return  (List<Map<String, Object>>) dao.findForList("HomeMapper.getEverydayOrder",pd);
	}
}
