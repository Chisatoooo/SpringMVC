package com.company.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.entity.User;
/**
 * 用户服务层接口
 * @author WYC
 *
 */
public interface UserService {
	//用户注册
	public void UserRegister(User user) throws Exception;
	//查询密码
	public String selectPwd(String username) throws Exception;
	//查询状态
	public String selectState(String username) throws Exception;
	//根据用户名查手机号
	public String queryPhone(String username) throws Exception;
	//根据用户名查地址
	public String queryAddress(String username) throws Exception;
	//查询某个用户全部信息
	public List<User> queryOneUser(String username) throws Exception;
	//用户修改密码
	public void UserUpdatePwd(String userpwd, String username) throws Exception;
	//用户修改生日
	public void UserUpdateBirthday(String birthday, String username) throws Exception;
	//用户修改联系方式
	public void UserUpdatePhone(String phone, String username) throws Exception;
	//用户修改地址
	public void UserUpdateAddress(String address, String username) throws Exception;
	//用户修改头像
	public void UserUpdateHeadimage(String headimage, String username) throws Exception;
	//查询全部用户信息
	public List<User> AdminSelectAllUser() throws Exception;
	//分页获取用户
	public List<User> AdminSelectPageUser(int offset, int count) throws Exception;
	//管理员注销用户
	public void AdminDeleteUser(String username) throws Exception;
	//管理员恢复用户
	public void AdminRecoverUser(String username) throws Exception;
}
