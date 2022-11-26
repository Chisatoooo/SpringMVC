package com.company.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.entity.ShoppingCar;

/**
 * 购物车服务层接口
 * @author WYC
 *
 */
public interface ShoppingCarService {
	//添加商品进购物车
	public void insert(ShoppingCar shoppingCar) throws Exception;
	//删除购物车的商品
	public void delete(int fk_id, String fk_username) throws Exception;
	//查询购物车的商品
	public List<ShoppingCar> queryShoppingCar(String fk_username) throws Exception;
	//根据用户名查商品编号
	public List<Integer> selectFkId(String fk_username) throws Exception;
	//更新商品数量
	public void updateNumber(int number, int fk_id, String fk_username) throws Exception;
	//更新商品总价
	public void updateSumprice(int sumprice, int fk_id, String fk_username) throws Exception;
	//查询购物车中某商品个数
	public int selectNumber(int fk_id, String fk_username) throws Exception;
	//查询购物车中某商品总价
	public int selectSumprice(int fk_id, String fk_username) throws Exception;
}
