package com.company.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.entity.User;
/**
 * �û������ӿ�
 * @author WYC
 *
 */
public interface UserService {
	//�û�ע��
	public void UserRegister(User user) throws Exception;
	//��ѯ����
	public String selectPwd(String username) throws Exception;
	//��ѯ״̬
	public String selectState(String username) throws Exception;
	//�����û������ֻ���
	public String queryPhone(String username) throws Exception;
	//�����û������ַ
	public String queryAddress(String username) throws Exception;
	//��ѯĳ���û�ȫ����Ϣ
	public List<User> queryOneUser(String username) throws Exception;
	//�û��޸�����
	public void UserUpdatePwd(String userpwd, String username) throws Exception;
	//�û��޸�����
	public void UserUpdateBirthday(String birthday, String username) throws Exception;
	//�û��޸���ϵ��ʽ
	public void UserUpdatePhone(String phone, String username) throws Exception;
	//�û��޸ĵ�ַ
	public void UserUpdateAddress(String address, String username) throws Exception;
	//�û��޸�ͷ��
	public void UserUpdateHeadimage(String headimage, String username) throws Exception;
	//��ѯȫ���û���Ϣ
	public List<User> AdminSelectAllUser() throws Exception;
	//��ҳ��ȡ�û�
	public List<User> AdminSelectPageUser(int offset, int count) throws Exception;
	//����Աע���û�
	public void AdminDeleteUser(String username) throws Exception;
	//����Ա�ָ��û�
	public void AdminRecoverUser(String username) throws Exception;
}
