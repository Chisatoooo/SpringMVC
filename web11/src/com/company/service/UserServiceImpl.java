package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.company.entity.User;
import com.company.mapper.UserMapper;
/**
 * 用户服务层接口实现
 * @author WYC
 *
 */
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
	public void UserRegister(User user) throws Exception {
		userMapper.insert(user);
	}

	public String selectPwd(String username) throws Exception {
		String userpwd = userMapper.selectPwd(username);
		return userpwd;
	}

	public String selectState(String username) throws Exception {
		String state = userMapper.selectState(username);
		return state;
	}

	public List<User> queryOneUser(String username) throws Exception {
		List<User> list = userMapper.queryOneUser(username);
		return list;
	}

	public void UserUpdateAddress(String address, String username) throws Exception {
		userMapper.updateAddress(address, username);
	}

	public void UserUpdateBirthday(String birthday, String username)
			throws Exception {
		userMapper.updateBirthday(birthday, username);
	}

	public void UserUpdatePhone(String phone, String username) throws Exception {
		userMapper.updatePhone(phone, username);
	}

	public void UserUpdatePwd(String userpwd, String username) throws Exception {
		userMapper.updatePwd(userpwd, username);
	}

	public void UserUpdateHeadimage(String headimage, String username)
			throws Exception {
		userMapper.updateHeadimage(headimage, username);
	}

	public List<User> AdminSelectAllUser() throws Exception {
		List<User> list = userMapper.selectAllUser();
		return list;
	}

	public List<User> AdminSelectPageUser(int offset, int count)
			throws Exception {
		List<User> list = userMapper.selectPageUser(offset * count, count);
		return list;
	}

	public void AdminDeleteUser(String username) throws Exception {
		userMapper.deleteUser(username);
	}

	public void AdminRecoverUser(String username) throws Exception {
		userMapper.recoverUser(username);
	}

	public String queryAddress(String username) throws Exception {
		String address = userMapper.queryAddress(username);
		return address;
	}

	public String queryPhone(String username) throws Exception {
		String phone = userMapper.queryPhone(username);
		return phone;
	}

}
