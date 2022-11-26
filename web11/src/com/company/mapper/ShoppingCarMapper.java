package com.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.entity.ShoppingCar;

/**
 * 购物车持久层接口
 * @author WYC
 *
 */
public interface ShoppingCarMapper {
	//添加商品进购物车
	public void insert(ShoppingCar shoppingCar) throws Exception;
	//删除购物车的商品
	public void delete(@Param("fk_id")int fk_id, @Param("fk_username")String fk_username) throws Exception;
	//查询购物车的商品
	public List<ShoppingCar> queryShoppingCar(String fk_username) throws Exception;
	//根据用户名查商品编号
	public List<Integer> selectFkId(String fk_username) throws Exception;
	//更新商品数量
	public void updateNumber(@Param("number")int number, @Param("fk_id")int fk_id, @Param("fk_username")String fk_username) throws Exception;
	//更新商品总价
	public void updateSumprice(@Param("sumprice")int sumprice, @Param("fk_id")int fk_id, @Param("fk_username")String fk_username) throws Exception;
	//查询购物车中某商品个数
	public int selectNumber(@Param("fk_id")int fk_id, @Param("fk_username")String fk_username) throws Exception;
	//查询购物车中某商品总价
	public int selectSumprice(@Param("fk_id")int fk_id, @Param("fk_username")String fk_username) throws Exception;
}
