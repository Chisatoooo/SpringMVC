package com.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.entity.ShoppingCar;

/**
 * ���ﳵ�־ò�ӿ�
 * @author WYC
 *
 */
public interface ShoppingCarMapper {
	//�����Ʒ�����ﳵ
	public void insert(ShoppingCar shoppingCar) throws Exception;
	//ɾ�����ﳵ����Ʒ
	public void delete(@Param("fk_id")int fk_id, @Param("fk_username")String fk_username) throws Exception;
	//��ѯ���ﳵ����Ʒ
	public List<ShoppingCar> queryShoppingCar(String fk_username) throws Exception;
	//�����û�������Ʒ���
	public List<Integer> selectFkId(String fk_username) throws Exception;
	//������Ʒ����
	public void updateNumber(@Param("number")int number, @Param("fk_id")int fk_id, @Param("fk_username")String fk_username) throws Exception;
	//������Ʒ�ܼ�
	public void updateSumprice(@Param("sumprice")int sumprice, @Param("fk_id")int fk_id, @Param("fk_username")String fk_username) throws Exception;
	//��ѯ���ﳵ��ĳ��Ʒ����
	public int selectNumber(@Param("fk_id")int fk_id, @Param("fk_username")String fk_username) throws Exception;
	//��ѯ���ﳵ��ĳ��Ʒ�ܼ�
	public int selectSumprice(@Param("fk_id")int fk_id, @Param("fk_username")String fk_username) throws Exception;
}
