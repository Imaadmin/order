package com.example.order.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户订单实体类
 *
 * @version 1.0
 */
@Table(name = "tb_orders")
public class UserOrder {

    private int id;
    private String orderOid;         //订单ID
    private int userId;              //用户id
    private int state;                //订单状态
    private Date createTime;          //添加时间
    private double total;               //金额

    private String phone;             //手机号

    private Long totalPage;               //统计数量时使用

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderOid() {
        return orderOid;
    }

    public void setOrderOid(String orderOid) {
        this.orderOid = orderOid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }
}
