package com.company.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.entity.ShoppingCar;

/**
 * ���ﳵ�����ӿ�
 * @author WYC
 *
 */
public interface ShoppingCarService {
	//�����Ʒ�����ﳵ
	public void insert(ShoppingCar shoppingCar) throws Exception;
	//ɾ�����ﳵ����Ʒ
	public void delete(int fk_id, String fk_username) throws Exception;
	//��ѯ���ﳵ����Ʒ
	public List<ShoppingCar> queryShoppingCar(String fk_username) throws Exception;
	//�����û�������Ʒ���
	public List<Integer> selectFkId(String fk_username) throws Exception;
	//������Ʒ����
	public void updateNumber(int number, int fk_id, String fk_username) throws Exception;
	//������Ʒ�ܼ�
	public void updateSumprice(int sumprice, int fk_id, String fk_username) throws Exception;
	//��ѯ���ﳵ��ĳ��Ʒ����
	public int selectNumber(int fk_id, String fk_username) throws Exception;
	//��ѯ���ﳵ��ĳ��Ʒ�ܼ�
	public int selectSumprice(int fk_id, String fk_username) throws Exception;
}
