package com.example.order.service.impl;

import com.example.order.dao.DaoSupport;
import com.example.order.entities.Banner;
import com.example.order.service.BannerService;
import com.example.order.util.PageData;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Banner
 */

@Service
public class BannerServiceImpl implements BannerService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	
	// 添加banner
	@Override
	public void addCoupon(PageData pd) throws Exception {

		pd.put("create_time",new Date());
		
		dao.save("BannerMapper.addCoupon", pd);
	}

	
	// 获取所有的优惠券
	@Override
	public List<Banner> getAllCoupons(PageData pd) throws Exception {
		
		pd.put("start", Integer.parseInt(pd.get("start").toString()));
		pd.put("length", Integer.parseInt(pd.get("length").toString()));
		pd.put("keyword", pd.get("search[value]").toString());
		
		return (List<Banner>) dao.findForList("BannerMapper.getAllCoupons", pd);
	}

	
	//删除优惠券
	@Override
	public void deleteCoupon(PageData pd) throws Exception {
		dao.delete("BannerMapper.deleteCoupon", pd);
	}

	
	//获取优惠券详情
	@Override
	public Banner getCouponInfo(PageData pd) throws Exception {
		return (Banner) dao.findForObject("BannerMapper.getCouponInfo", pd);
	}

	// 更新优惠券
	@Override
	public void updateCoupon(PageData pd) throws Exception {
		dao.update("BannerMapper.updateCoupon", pd);
	}
}
