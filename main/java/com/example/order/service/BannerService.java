package com.example.order.service;


import com.example.order.entities.Banner;
import com.example.order.util.PageData;

import java.util.List;

/**
 *
 */
public interface BannerService {


	/**
	 * 添加优惠券
	 * @author ZhouXiaobing 
	 * @date 2018年3月31日 上午10:58:09 
	 * @param pd
	 * @throws Exception 
	 */
	void addCoupon(PageData pd) throws Exception;


	/**
	 * 获取所有的优惠券
	 * @author ZhouXiaobing 
	 * @date 2018年3月31日 上午10:58:39 
	 * @param pd
	 * @return
	 * @throws Exception 
	 */
	List<Banner> getAllCoupons(PageData pd) throws Exception;
	

	/**
	 * 删除优惠券
	 * @author ZhouXiaobing 
	 * @date 2018年3月31日 上午10:58:53 
	 * @param pd
	 * @throws Exception 
	 */
	void deleteCoupon(PageData pd) throws Exception;
	
	
	/**
	 * 获取优惠券详情
	 * @author ZhouXiaobing 
	 * @date 2018年3月31日 上午10:59:15 
	 * @param pd
	 * @return
	 * @throws Exception 
	 */
	Banner getCouponInfo(PageData pd) throws Exception;
	
	
	/**
	 * 更新优惠券
	 * @author ZhouXiaobing 
	 * @date 2018年3月31日 上午10:59:32 
	 * @param pd
	 * @throws Exception 
	 */
	 void updateCoupon(PageData pd) throws Exception;
}