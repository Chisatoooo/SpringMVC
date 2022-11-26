package com.company.service;
/**
 * 管理员服务层接口
 * @author WYC
 *
 */
public interface AdminService {
	//查询管理员密码
	public String adminSelectPwd(String adminname) throws Exception;
}
