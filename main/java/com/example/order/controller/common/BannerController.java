package com.example.order.controller.common;

import com.example.order.controller.Base.BaseController;
import com.example.order.controller.Base.InterfaceResult;
import com.example.order.entities.Banner;
import com.example.order.service.BannerService;
import com.example.order.util.FileUpdate;
import com.example.order.util.PageData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.util.List;

/**
 * Banner管理
 */
@RestController
public class BannerController extends BaseController {

	@Resource
	BannerService bannerService;


	/**
	 * 添加Banner
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/banner/addCoupon")
	public Object addCoupon() throws Exception{
		PageData pd=this.getPageData();

		//调用添加banner图的方法
		bannerService.addCoupon(pd);
		return InterfaceResult.returnSuccess(null);
	}


	/**
	 * 获取所有banner
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/banner/getAllCoupon")
	public Object getAllCoupon() throws Exception{
		PageData pd = this.getPageData();
		
	    List<Banner> couponns= bannerService.getAllCoupons(pd);
	    Long total = couponns.size() == 0 ? 0L : couponns.get(0).getTotal();
	    
		return InterfaceResult.returnTableSuccess(couponns, total, pd.get("draw"));
	}


	/**
	 * 删除Banner
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/banner/deletCoupon")
	public Object deletCoupon() throws Exception {
		PageData pd = this.getPageData();

		bannerService.deleteCoupon(pd);
		
		return InterfaceResult.returnSuccess(null);
	}


	/**
	 * 获取Banner详情
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/banner/getCouponInfo")
	public Object getCouponInfo() throws Exception{
		PageData pd = this.getPageData();
		
		Banner banner=bannerService.getCouponInfo(pd);
		
		return InterfaceResult.returnSuccess(banner);
	}


	/**
	 * 更新Banner
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/banner/updateCoupon")
	public Object updateCoupon() throws Exception{
		PageData pd=this.getPageData();

		bannerService.updateCoupon(pd);
		
		return InterfaceResult.returnSuccess(null);
	}


	/**
	 * 图片上传
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/banner/fileUpload")
	public Object fileUpload(MultipartFile file){
		FileUpdate fileUpdate = new FileUpdate();
		if(file != null) {
			try {
				String url = fileUpdate.fileUpdate(file);
				return InterfaceResult.returnSuccess(url);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return InterfaceResult.returnSuccess(null);
	}
	
}
