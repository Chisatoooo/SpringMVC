package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.company.entity.ShoppingCar;
import com.company.mapper.ShoppingCarMapper;
/**
 * 购物车服务层接口实现
 * @author WYC
 *
 */
@Transactional
public class ShoppingCarServiceImpl implements ShoppingCarService {
	@Autowired
	private ShoppingCarMapper shoppingCarMapper;
	
	public void delete(int fk_id, String fkUsername) throws Exception {
		shoppingCarMapper.delete(fk_id, fkUsername);
	}

	public void insert(ShoppingCar shoppingCar) throws Exception {
		shoppingCarMapper.insert(shoppingCar);
	}

	public List<ShoppingCar> queryShoppingCar(String fkUsername)
			throws Exception {
		List<ShoppingCar> list = shoppingCarMapper.queryShoppingCar(fkUsername);
		return list;
	}

	public List<Integer> selectFkId(String fkUsername) throws Exception {
		List<Integer> list = shoppingCarMapper.selectFkId(fkUsername);
		return list;
	}

	public int selectNumber(int fk_id, String fk_username) throws Exception {
		int number = shoppingCarMapper.selectNumber(fk_id, fk_username);
		return number;
	}

	public int selectSumprice(int fk_id, String fk_username) throws Exception {
		int sumprice = shoppingCarMapper.selectSumprice(fk_id, fk_username);
		return sumprice;
	}

	public void updateNumber(int number, int fk_id, String fkUsername)
			throws Exception {
		shoppingCarMapper.updateNumber(number, fk_id, fkUsername);
	}

	public void updateSumprice(int sumprice, int fk_id, String fkUsername)
			throws Exception {
		shoppingCarMapper.updateSumprice(sumprice, fk_id, fkUsername);
	}

}
