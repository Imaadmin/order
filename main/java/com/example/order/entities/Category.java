package com.example.order.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * 分类实体
 */

@Table(name = "tb_category")
public class Category {
    private Integer id;
    @Column(name = "category_name")
    private String categoryName;
    private Date createTime;
    private Long total;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
