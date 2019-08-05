
package com.example.order.controller.common;

import com.example.order.controller.Base.BaseController;
import com.example.order.controller.Base.InterfaceResult;
import com.example.order.entities.Category;
import com.example.order.entities.Variety;
import com.example.order.service.CategoriesService;
import com.example.order.service.VarietyService;
import com.example.order.util.FileUpdate;
import com.example.order.util.FtpFileUtil;
import com.example.order.util.PageData;
import com.example.order.util.SpringBeansUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜品管理
 * @author 李磊
 * @version 1.0
 */
@RestController
public class VarietyController extends BaseController {

    @Resource
	VarietyService varietyService;

	@Resource
	CategoriesService categoriesService;

	@Autowired
	private SpringBeansUtil beans;


	/**
	 *获取菜品列表
	 */
	@RequestMapping("/variety/getVarietyList")
	public Object getVarietyList() throws Exception {
		PageData pd = this.getPageData();
		List<Variety> list = varietyService.getVarietyList(pd);
		Long recordsTotal = list.size() == 0 ? 0l:list.get(0).getTotal();
		return InterfaceResult.returnTableSuccess(list, recordsTotal, pd.get("draw"));
	}


	/**
	 * 查找所以分类
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/variety/getAllCategories")
	public Object getAllCategories() throws Exception {
		PageData pd = this.getPageData();
		List<Category> categoriesList = categoriesService.getAllCategories(pd);
		return InterfaceResult.returnSuccess(categoriesList);
	}


	/**
	 * 新增菜品
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/variety/addVariety")
	public Object addVariety() throws Exception {
		PageData pd = this.getPageData();
		varietyService.addVariety(pd);
		return InterfaceResult.returnSuccess(null);
	}


	/**
	 * 图片上传
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/variety/fileUpload")
	public Object fileUpload(MultipartFile file) throws Exception {
		FileUpdate fileUpdate = new FileUpdate();
		if(file != null){
			try {
				String url =  fileUpdate.fileUpdate(file);
				return InterfaceResult.returnSuccess(url);
			}catch (Exception ex){
				ex.printStackTrace();
			}
		}
		return InterfaceResult.returnSuccess(null);
	}
}
