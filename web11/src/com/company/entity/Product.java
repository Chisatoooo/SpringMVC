package com.company.entity;

/**
 * id: ��Ʒ���
 * name: ��Ʒ����
 * price: ��Ʒ�۸�
 * image: ��ƷͼƬ
 * type: ��Ʒ����
 * state: ��Ʒ״̬
 * @author WYC
 *
 */
public class Product {
	private int id;
	private String name;
	private int price;
	private String image;
	private String type;
	private String state = "���ϼ�";
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", image=" + image + ", name=" + name
				+ ", price=" + price + ", state=" + state + ", type=" + type
				+ "]";
	}
	
}
