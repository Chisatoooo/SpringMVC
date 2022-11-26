package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.company.entity.Orders;
import com.company.mapper.OrdersMapper;
/**
 * 订单服务层接口实现
 * @author WYC
 *
 */
@Transactional
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	private OrdersMapper ordersMapper;
	
	public List<Orders> AdminQueryOrders() throws Exception {
		List<Orders> list = ordersMapper.AdminQueryOrders();
		return list;
	}

	public List<Orders> UserQueryOrders(String fk_order_username)
			throws Exception {
		List<Orders> list = ordersMapper.UserQueryOrders(fk_order_username);
		return list;
	}

	public void cancelOrder(int oid) throws Exception {
		ordersMapper.cancelOrder(oid);
	}

	public void insert(Orders orders) throws Exception {
		ordersMapper.insert(orders);
	}

	public void updateDeliverystate(int oid) throws Exception {
		ordersMapper.updateDeliverystate(oid);
	}

}
