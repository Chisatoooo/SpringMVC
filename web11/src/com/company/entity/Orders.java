package com.company.entity;

/**
 * oid: 订单编号
 * fk_order_username: 用户名
 * fk_phone: 用户手机号
 * fk_address: 用户地址
 * fk_product_id: 商品编号
 * paystate: 支付状态
 * date: 日期
 * deliverystate: 发货状态
 * orderstate: 订单状态
 * @author WYC
 *
 */
public class Orders {
	private int oid;
	private String fk_order_username;
	private int fk_product_id;
	private String fk_phone;
	private String fk_address;
	private String paystate = "已支付";
	private String date;
	private String deliverystate = "待发货";
	private String orderstate = "未取消";

	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getFk_order_username() {
		return fk_order_username;
	}
	public void setFk_order_username(String fkOrderUsername) {
		fk_order_username = fkOrderUsername;
	}
	public int getFk_product_id() {
		return fk_product_id;
	}
	public void setFk_product_id(int fkProductId) {
		fk_product_id = fkProductId;
	}
	public String getFk_phone() {
		return fk_phone;
	}
	public void setFk_phone(String fkPhone) {
		fk_phone = fkPhone;
	}
	public String getFk_address() {
		return fk_address;
	}
	public void setFk_address(String fkAddress) {
		fk_address = fkAddress;
	}

	public String getPaystate() {
		return paystate;
	}
	public void setPaystate(String paystate) {
		this.paystate = paystate;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDeliverystate() {
		return deliverystate;
	}
	public void setDeliverystate(String deliverystate) {
		this.deliverystate = deliverystate;
	}
	public String getOrderstate() {
		return orderstate;
	}
	public void setOrderstate(String orderstate) {
		this.orderstate = orderstate;
	}
	@Override
	public String toString() {
		return "Orders [date=" + date + ", deliverystate=" + deliverystate
				+ ", fk_address=" + fk_address + ", fk_order_username="
				+ fk_order_username + ", fk_phone=" + fk_phone
				+ ", fk_product_id=" + fk_product_id + ", oid=" + oid
				+ ", orderstate=" + orderstate + ", paystate=" + paystate + "]";
	}

}
