package com.company.entity;

/**
 * oid: �������
 * fk_order_username: �û���
 * fk_phone: �û��ֻ���
 * fk_address: �û���ַ
 * fk_product_id: ��Ʒ���
 * paystate: ֧��״̬
 * date: ����
 * deliverystate: ����״̬
 * orderstate: ����״̬
 * @author WYC
 *
 */
public class Orders {
	private int oid;
	private String fk_order_username;
	private int fk_product_id;
	private String fk_phone;
	private String fk_address;
	private String paystate = "��֧��";
	private String date;
	private String deliverystate = "������";
	private String orderstate = "δȡ��";

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
