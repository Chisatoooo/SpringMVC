package com.company.service;

import java.util.List;

import com.company.entity.Orders;

/**
 * ���������ӿ�
 * @author WYC
 *
 */
public interface OrdersService {
	//���Ӷ���
	public void insert(Orders orders) throws Exception;
	//ȡ������
	public void cancelOrder(int oid) throws Exception;
	//����Ա��ѯȫ��������Ϣ
	public List<Orders> AdminQueryOrders() throws Exception;
	//�û���ѯ����
	public List<Orders> UserQueryOrders(String fk_order_username) throws Exception;
	//����Ա�޸ķ���״̬
	public void updateDeliverystate(int oid) throws Exception;
}