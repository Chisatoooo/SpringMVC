package com.company.entity;

/**
 * sid: 购物车编号
 * fk_username: 用户名
 * fk_id: 商品编号
 * productname: 商品名称
 * number: 数量
 * price: 单价
 * sumprice: 总价
 * @author WYC
 *
 */
public class ShoppingCar {
	private int sid;
	private String fk_username;
	private int fk_id;
	private String productname;
	private int number;
	private int price;
	private int sumprice;
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getFk_username() {
		return fk_username;
	}
	public void setFk_username(String fkUsername) {
		fk_username = fkUsername;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSumprice() {
		return sumprice;
	}
	public void setSumprice(int sumprice) {
		this.sumprice = sumprice;
	}
	public int getFk_id() {
		return fk_id;
	}
	public void setFk_id(int fkId) {
		fk_id = fkId;
	}
	@Override
	public String toString() {
		return "ShoppingCar [fk_id=" + fk_id + ", fk_username=" + fk_username
				+ ", sid=" + sid + ", number=" + number + ", price=" + price
				+ ", productname=" + productname + ", sumprice=" + sumprice
				+ "]";
	}
}
