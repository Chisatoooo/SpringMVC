package com.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.entity.Product;

/**
 * 商品持久层接口
 * @author WYC
 *
 */
public interface ProductMapper {
	//添加商品
	public void insert(Product product) throws Exception;
	//下架商品
	public void deleteProduct(int id) throws Exception;
	//恢复商品
	public void recoverProduct(int id) throws Exception;
	//用户模糊查询商品
	public List<Product> UserFuzzyQueryProduct(String context) throws Exception;
	//用户查询全部商品信息
	public List<Product> UserQueryProduct() throws Exception;
	//用户分页获取全部商品
	public List<Product> UserQueryProductPage(@Param("offset")int offset, @Param("count")int count) throws Exception;
	//根据id查询商品信息
	public List<Product> AdminQueryProductById(int id) throws Exception;
	//用户分页获取模糊查询商品
	public List<Product> UserFuzzyQueryProductPage(@Param("context")String context, @Param("offset")int offset, @Param("count")int count) throws Exception;
	//管理员查看全部商品
	public List<Product> AdminQueryProduct() throws Exception;
	//管理员分页查看全部商品
	public List<Product> AdminQueryProductPage(@Param("offset")int offset, @Param("count")int count) throws Exception;
	//管理员模糊查询商品
	public List<Product> AdminFuzzyQuery(String context) throws Exception;
	//管理员分页获取模糊查询商品
	public List<Product> AdminFuzzyQueryProductPage(@Param("context")String context, @Param("offset")int offset, @Param("count")int count) throws Exception;
	//管理员修改商品价格
	public void AdminUpdatePrice(@Param("price")int price, @Param("id")int id) throws Exception;
}
