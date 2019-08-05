package com.example.order.entities;

/**
 *数据统计实体
 */
public class Home{
	
	private Long totalVariety;		//菜品数
	private Long totalUsers;		//平台总用户数
	private Long totalOrder;		//平台订单总数


	private Long totalIncome;		//平台总收入

	public Long getTotalVariety() {
		return totalVariety;
	}

	public void setTotalVariety(Long totalVariety) {
		this.totalVariety = totalVariety;
	}

	public Long getTotalUsers() {
		return totalUsers;
	}

	public void setTotalUsers(Long totalUsers) {
		this.totalUsers = totalUsers;
	}

	public Long getTotalOrder() {
		return totalOrder;
	}

	public void setTotalOrder(Long totalOrder) {
		this.totalOrder = totalOrder;
	}

	public Long getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(Long totalIncome) {
		this.totalIncome = totalIncome;
	}
}
