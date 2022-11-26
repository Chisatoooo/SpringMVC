package com.company.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.entity.Product;
/**
 * 商品服务层接口
 * @author WYC
 *
 */
public interface ProductService {
	//管理员添加商品
	public void AdminAddProduct(Product product) throws Exception;
	//管理员下架商品
	public void AdminDeleteProduct(int id) throws Exception;
	//管理员恢复商品
	public void AdminRecoverProduct(int id) throws Exception;
	//用户模糊查询商品
	public List<Product> UserFuzzyQueryProduct(String context) throws Exception;
	//用户查询全部商品信息
	public List<Product> UserQueryProduct() throws Exception;
	//用户分页获取全部商品
	public List<Product> UserQueryProductPage(int offset, int count) throws Exception;
	//根据id查询商品信息
	public List<Product> AdminQueryProductById(int id) throws Exception;
	//用户分页获取模糊查询商品
	public List<Product> UserFuzzyQueryProductPage(String context, int offset, int count) throws Exception;
	//管理员查看全部商品
	public List<Product> AdminQueryProduct() throws Exception;
	//管理员分页查看全部商品
	public List<Product> AdminQueryProductPage(int offset, int count) throws Exception;
	//管理员模糊查询商品
	public List<Product> AdminFuzzyQueryProduct(String context) throws Exception;
	//管理员分页获取模糊查询商品
	public List<Product> AdminFuzzyQueryProductPage(String context, int offset, int count) throws Exception;
	//管理员修改商品价格
	public void AdminUpdatePrice(int price, int id) throws Exception;
}
