package com.company.mapper;

import java.util.List;

import com.company.entity.Orders;

/**
 * 订单持久层接口
 * @author WYC
 *
 */
public interface OrdersMapper {
	//添加订单
	public void insert(Orders orders) throws Exception;
	//取消订单
	public void cancelOrder(int oid) throws Exception;
	//管理员查询全部订单信息
	public List<Orders> AdminQueryOrders() throws Exception;
	//用户查询订单
	public List<Orders> UserQueryOrders(String fk_order_username) throws Exception;
	//管理员修改发货状态
	public void updateDeliverystate(int oid) throws Exception;
}
