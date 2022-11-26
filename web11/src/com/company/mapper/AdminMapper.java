package com.company.mapper;
/**
 * 管理员持久层接口
 * @author WYC
 *
 */
public interface AdminMapper {
	//查询管理员密码
	public String adminSelectPwd(String adminname) throws Exception;
}
