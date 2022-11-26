package com.company.entity;

/**
 * username: 用户名
 * userpwd: 用户密码
 * phone: 用户联系方式
 * uname: 用户姓名
 * IDnumber: 用户身份证
 * date: 用户创建日期
 * birthday: 用户生日
 * address: 用户地址
 * headimage: 用户头像
 * state: 用户状态
 * @author WYC
 *
 */
public class User {
	private String username;
	private String userpwd;
	private String phone;
	private String uname;
	private String IDnumber;
	private String date;
	private String birthday = "无信息";
	private String address = "无信息";
	private String headimage = "default.jpg";
	private String state = "已注册";
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getIDnumber() {
		return IDnumber;
	}
	public void setIDnumber(String iDnumber) {
		IDnumber = iDnumber;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHeadimage() {
		return headimage;
	}
	public void setHeadimage(String headimage) {
		this.headimage = headimage;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "User [IDnumber=" + IDnumber + ", address=" + address
				+ ", birthday=" + birthday + ", date=" + date + ", headimage="
				+ headimage + ", uname=" + uname + ", phone=" + phone
				+ ", state=" + state + ", username=" + username + ", userpwd="
				+ userpwd + "]";
	}

}
