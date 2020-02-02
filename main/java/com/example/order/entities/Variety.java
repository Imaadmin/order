package com.example.order.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * 菜品实体类
 *
 * @version 1.0
 */
@Table(name = "tb_variety")
public class Variety {

    private int id;                           //ID
    private int categoryId;                   //分类ID
    private String varietyName;              //菜品名
    private String contents;                  //介绍
    private double price;                     //价格
    private int count;                     //数量
    private String url;                       //图片地址
    private Date createTime;                  //添加时间

    private String categoryName;              //分类名

    private Long total;                       //统计数量时使用

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getVarietyName() {
        return varietyName;
    }

    public void setVarietyName(String varietyName) {
        this.varietyName = varietyName;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
