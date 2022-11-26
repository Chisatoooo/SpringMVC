package com.company.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.entity.Product;
/**
 * ��Ʒ�����ӿ�
 * @author WYC
 *
 */
public interface ProductService {
	//����Ա�����Ʒ
	public void AdminAddProduct(Product product) throws Exception;
	//����Ա�¼���Ʒ
	public void AdminDeleteProduct(int id) throws Exception;
	//����Ա�ָ���Ʒ
	public void AdminRecoverProduct(int id) throws Exception;
	//�û�ģ����ѯ��Ʒ
	public List<Product> UserFuzzyQueryProduct(String context) throws Exception;
	//�û���ѯȫ����Ʒ��Ϣ
	public List<Product> UserQueryProduct() throws Exception;
	//�û���ҳ��ȡȫ����Ʒ
	public List<Product> UserQueryProductPage(int offset, int count) throws Exception;
	//����id��ѯ��Ʒ��Ϣ
	public List<Product> AdminQueryProductById(int id) throws Exception;
	//�û���ҳ��ȡģ����ѯ��Ʒ
	public List<Product> UserFuzzyQueryProductPage(String context, int offset, int count) throws Exception;
	//����Ա�鿴ȫ����Ʒ
	public List<Product> AdminQueryProduct() throws Exception;
	//����Ա��ҳ�鿴ȫ����Ʒ
	public List<Product> AdminQueryProductPage(int offset, int count) throws Exception;
	//����Աģ����ѯ��Ʒ
	public List<Product> AdminFuzzyQueryProduct(String context) throws Exception;
	//����Ա��ҳ��ȡģ����ѯ��Ʒ
	public List<Product> AdminFuzzyQueryProductPage(String context, int offset, int count) throws Exception;
	//����Ա�޸���Ʒ�۸�
	public void AdminUpdatePrice(int price, int id) throws Exception;
}
