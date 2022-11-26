package com.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.company.mapper.AdminMapper;

/**
 * 用户服务层接口实现
 * @author WYC
 *
 */
@Transactional
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminMapper adminMapper;
	
	public String adminSelectPwd(String adminname) throws Exception {
		String pwd = adminMapper.adminSelectPwd(adminname);
		return pwd;
	}

}
