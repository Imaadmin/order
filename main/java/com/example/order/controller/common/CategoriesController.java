
package com.example.order.controller.common;

import com.example.order.controller.Base.BaseController;
import com.example.order.controller.Base.InterfaceResult;
import com.example.order.entities.Category;
import com.example.order.service.CategoriesService;
import com.example.order.util.PageData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;


/**
 * 分类管理
 *
 * @author 李磊
 * @version 1.0
 */
@RestController
public class CategoriesController extends BaseController {

    @Resource
    CategoriesService categoriesService;


    /**
     * 分类列表
     */
    @RequestMapping("/categories/getCategoriesList")
    public Object getCategoriesList() throws Exception {
        PageData pd = this.getPageData();
        List<Category> list = categoriesService.getCategoriesList(pd);
        Long recordsTotal = list.size() == 0 ? 0L : list.get(0).getTotal();
        return InterfaceResult.returnTableSuccess(list, recordsTotal, pd.get("draw"));
    }


    /**
     * 根据id删除分类
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/categories/deleteCategory")
    public Object deleteCategory() throws Exception {
        PageData pd = this.getPageData();
        //判断有没有子分类
        List<Category>c = categoriesService.selectChildren(pd);
        int cunt = c.size();
        System.out.println(cunt);
        if (cunt>0){
           return InterfaceResult.returnFailureWithMsg("删除失败！存在子分类");
        }else {
            categoriesService.deleteCategory(pd);
        }
        return InterfaceResult.returnSuccess(null);
    }


    /**
     * 添加父分类
     *
     * @return
     */
    @RequestMapping("/categories/addCategory")
    public Object addCategory() throws Exception {
        PageData pd=this.getPageData();
        categoriesService.addCategory(pd);
        return InterfaceResult.returnSuccess(null);
    }


    /**
     * 添加子分类
     *
     * @return
     */
    @RequestMapping("/categories/addSunCategory")
    public Object addSunCategory() throws Exception {
        PageData pd=this.getPageData();
        categoriesService.addSunCategory(pd);
        return InterfaceResult.returnSuccess(null);
    }


    /**
     * 查找所以父分类
     * @return
     * @throws Exception
     */
    @RequestMapping("/categories/getAllFatherCategories")
    public Object getAllFatherCategories() throws Exception {
        PageData pd = this.getPageData();
        List<Category> categoriesList = categoriesService.getAllFatherCategories(pd);
        return InterfaceResult.returnSuccess(categoriesList);
    }


}
