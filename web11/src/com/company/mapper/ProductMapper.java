package com.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.entity.Product;

/**
 * ��Ʒ�־ò�ӿ�
 * @author WYC
 *
 */
public interface ProductMapper {
	//�����Ʒ
	public void insert(Product product) throws Exception;
	//�¼���Ʒ
	public void deleteProduct(int id) throws Exception;
	//�ָ���Ʒ
	public void recoverProduct(int id) throws Exception;
	//�û�ģ����ѯ��Ʒ
	public List<Product> UserFuzzyQueryProduct(String context) throws Exception;
	//�û���ѯȫ����Ʒ��Ϣ
	public List<Product> UserQueryProduct() throws Exception;
	//�û���ҳ��ȡȫ����Ʒ
	public List<Product> UserQueryProductPage(@Param("offset")int offset, @Param("count")int count) throws Exception;
	//����id��ѯ��Ʒ��Ϣ
	public List<Product> AdminQueryProductById(int id) throws Exception;
	//�û���ҳ��ȡģ����ѯ��Ʒ
	public List<Product> UserFuzzyQueryProductPage(@Param("context")String context, @Param("offset")int offset, @Param("count")int count) throws Exception;
	//����Ա�鿴ȫ����Ʒ
	public List<Product> AdminQueryProduct() throws Exception;
	//����Ա��ҳ�鿴ȫ����Ʒ
	public List<Product> AdminQueryProductPage(@Param("offset")int offset, @Param("count")int count) throws Exception;
	//����Աģ����ѯ��Ʒ
	public List<Product> AdminFuzzyQuery(String context) throws Exception;
	//����Ա��ҳ��ȡģ����ѯ��Ʒ
	public List<Product> AdminFuzzyQueryProductPage(@Param("context")String context, @Param("offset")int offset, @Param("count")int count) throws Exception;
	//����Ա�޸���Ʒ�۸�
	public void AdminUpdatePrice(@Param("price")int price, @Param("id")int id) throws Exception;
}
