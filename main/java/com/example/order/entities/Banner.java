package com.example.order.entities;

import javax.persistence.Table;
import java.util.Date;

/**
 * banner实体
 */
@Table(name = "tb_banner")
public class Banner {


	private int id;					//主键
	private String bannerImg;		//banner图片url
	private String bannerUrl;		//banner跳转地址
	private String contents;		//简介
	private int state;				//状态：0-上线 1-下线
	private Date createTime;		//发布时间
	private Long total;				//统计数量

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBannerImg() {
		return bannerImg;
	}

	public void setBannerImg(String bannerImg) {
		this.bannerImg = bannerImg;
	}

	public String getBannerUrl() {
		return bannerUrl;
	}

	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
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

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}
