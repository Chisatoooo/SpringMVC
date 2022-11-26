package com.company.mapper;
/**
 * 用户持久层接口
 * @author WYC
 *
 */
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.company.entity.User;
@Mapper
public interface UserMapper {
	//添加用户
	public void insert(User user) throws Exception;
	//查询密码
	public String selectPwd(String username) throws Exception;
	//查询状态
	public String selectState(String username) throws Exception;
	//查询某个用户全部信息
	public List<User> queryOneUser(String username) throws Exception;
	//根据用户名查手机号
	public String queryPhone(String username) throws Exception;
	//根据用户名查地址
	public String queryAddress(String username) throws Exception;
	//修改密码
	public void updatePwd(@Param("userpwd")String userpwd, @Param("username")String username) throws Exception;
	//修改生日
	public void updateBirthday(@Param("birthday")String birthday, @Param("username")String username) throws Exception;
	//修改联系方式
	public void updatePhone(@Param("phone")String phone, @Param("username")String username) throws Exception;
	//修改地址
	public void updateAddress(@Param("address")String address, @Param("username")String username) throws Exception;
	//修改头像
	public void updateHeadimage(@Param("headimage")String headimage, @Param("username")String username) throws Exception;
	//查询全部用户信息
	public List<User> selectAllUser() throws Exception;
	//分页获取用户
	public List<User> selectPageUser(@Param("offset")int offset, @Param("count")int count) throws Exception;
	//注销用户
	public void deleteUser(String username) throws Exception;
	//恢复用户
	public void recoverUser(String username) throws Exception;
}
