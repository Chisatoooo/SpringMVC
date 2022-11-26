package com.company.mapper;
/**
 * �û��־ò�ӿ�
 * @author WYC
 *
 */
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.company.entity.User;
@Mapper
public interface UserMapper {
	//����û�
	public void insert(User user) throws Exception;
	//��ѯ����
	public String selectPwd(String username) throws Exception;
	//��ѯ״̬
	public String selectState(String username) throws Exception;
	//��ѯĳ���û�ȫ����Ϣ
	public List<User> queryOneUser(String username) throws Exception;
	//�����û������ֻ���
	public String queryPhone(String username) throws Exception;
	//�����û������ַ
	public String queryAddress(String username) throws Exception;
	//�޸�����
	public void updatePwd(@Param("userpwd")String userpwd, @Param("username")String username) throws Exception;
	//�޸�����
	public void updateBirthday(@Param("birthday")String birthday, @Param("username")String username) throws Exception;
	//�޸���ϵ��ʽ
	public void updatePhone(@Param("phone")String phone, @Param("username")String username) throws Exception;
	//�޸ĵ�ַ
	public void updateAddress(@Param("address")String address, @Param("username")String username) throws Exception;
	//�޸�ͷ��
	public void updateHeadimage(@Param("headimage")String headimage, @Param("username")String username) throws Exception;
	//��ѯȫ���û���Ϣ
	public List<User> selectAllUser() throws Exception;
	//��ҳ��ȡ�û�
	public List<User> selectPageUser(@Param("offset")int offset, @Param("count")int count) throws Exception;
	//ע���û�
	public void deleteUser(String username) throws Exception;
	//�ָ��û�
	public void recoverUser(String username) throws Exception;
}
