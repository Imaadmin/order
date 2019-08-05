package com.example.order.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * 报名用户实体类
 * @version 1.0
 */
@Table(name = "tb_user")
public class User {
	private int id;							//用户ID
	private String phone;					//手机号
	private Date createTime;				//添加时间
	private Long total;						//统计数量时使用

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}
