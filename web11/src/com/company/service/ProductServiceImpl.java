package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.company.entity.Product;
import com.company.mapper.ProductMapper;
/**
 * 用户服务层接口实现
 * @author WYC
 *
 */
@Transactional
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductMapper productMapper;

	public List<Product> AdminFuzzyQueryProduct(String context) throws Exception {
		List<Product> list = productMapper.AdminFuzzyQuery(context);
		return list;
	}

	public List<Product> AdminFuzzyQueryProductPage(String context, int offset,
			int count) throws Exception {
		List<Product> list = productMapper.AdminFuzzyQueryProductPage(context, offset * count, count);
		return list;
	}

	public List<Product> AdminQueryProduct() throws Exception {
		List<Product> list = productMapper.AdminQueryProduct();
		return list;
	}

	public List<Product> AdminQueryProductById(int id) throws Exception {
		List<Product> list = productMapper.AdminQueryProductById(id);
		return list;
	}

	public List<Product> AdminQueryProductPage(int offset, int count)
			throws Exception {
		List<Product> list = productMapper.AdminQueryProductPage(offset * count, count);
		return list;
	}

	public void AdminUpdatePrice(int price, int id) throws Exception {
		productMapper.AdminUpdatePrice(price, id);
	}

	public List<Product> UserFuzzyQueryProduct(String context) throws Exception {
		List<Product> list = productMapper.UserFuzzyQueryProduct(context);
		return list;
	}

	public List<Product> UserFuzzyQueryProductPage(String context, int offset,
			int count) throws Exception {
		List<Product> list = productMapper.UserFuzzyQueryProductPage(context, offset * count, count);
		return list;
	}

	public List<Product> UserQueryProduct() throws Exception {
		List<Product> list = productMapper.UserQueryProduct();
		return list;
	}

	public List<Product> UserQueryProductPage(int offset, int count)
			throws Exception {
		List<Product> list = productMapper.UserQueryProductPage(offset * count, count);
		return list;
	}

	public void AdminAddProduct(Product product) throws Exception {
		productMapper.insert(product);
	}

	public void AdminDeleteProduct(int id) throws Exception {
		productMapper.deleteProduct(id);
	}

	public void AdminRecoverProduct(int id) throws Exception {
		productMapper.recoverProduct(id);
	}

}
