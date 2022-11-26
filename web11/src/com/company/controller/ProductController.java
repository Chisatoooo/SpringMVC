package com.company.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.company.entity.Product;
import com.company.entity.User;
import com.company.service.ProductService;
import com.company.service.UserService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	
	//用户跳转商品页面
	@RequestMapping("/userSkipProduct")
	public void userSkipProduct(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int allPage = 0;
		try {
			List<Product> list = productService.UserQueryProduct();
			//计算总页数
			int count = list.size();
			allPage = count / 3;
			if(count % 3 != 0){
				allPage = allPage + 1;
			}
			//将总页数及当前页数存在session域对象中
			HttpSession hs = request.getSession();
			hs.setAttribute("allPage", allPage);
			hs.setAttribute("currentPage", "1");
			//获取第一批3个商品对象
			List<Product> productList = productService.UserQueryProductPage(0, 3);
			request.setAttribute("productList", productList);
			request.getRequestDispatcher("WEB-INF/jsp/Product.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//用户查看下一页商品
	@RequestMapping("/userNextProductPage")
	public void userNextProductPage(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取上一页页码及总页码数
		HttpSession hs = request.getSession();
		String currentPage = (String) hs.getAttribute("currentPage");
		int allPage = (Integer) hs.getAttribute("allPage");
		//System.out.println("下一页: " + allPage + " : " + currentPage);
		//是否为倒数第二页
		if(Integer.valueOf(currentPage) < (allPage - 1)){ //不是倒数第二页
			try {
				List<Product> productList = productService.UserQueryProductPage(Integer.valueOf(currentPage), 3);
				request.setAttribute("productList", productList);
				currentPage = String.valueOf((Integer.valueOf(currentPage) + 1));
				request.setAttribute("currentPage", currentPage);
				hs.setAttribute("currentPage", currentPage);
				request.getRequestDispatcher("WEB-INF/jsp/Product.jsp").forward(request, response);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{ //是倒数第二页
			List<Product> productList = null;
			try {
				int cp = Integer.valueOf(currentPage);
				if(cp >= allPage){
					cp = allPage - 1;
				}
				productList = productService.UserQueryProductPage(cp, 3);
				request.setAttribute("productList", productList);
				currentPage = String.valueOf(allPage);
				request.setAttribute("currentPage", currentPage);
				hs.setAttribute("currentPage", currentPage);
				request.setAttribute("nextPage", "尾页");
				request.getRequestDispatcher("WEB-INF/jsp/Product.jsp").forward(request, response);		
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//用户查看上一页商品
	@RequestMapping("/userPreviousProductPage")
	public void userPreviousProductPage(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取下一页页码及总页码数
		HttpSession hs = request.getSession();
		String currentPage = (String) hs.getAttribute("currentPage");
		int allPage = (Integer) hs.getAttribute("allPage");
		//System.out.println("上一页: " + allPage + " : " + currentPage);
		//是否为第二页
		if(Integer.valueOf(currentPage) >= 2){ //不是第二页
			try {
				List<Product> productList = productService.UserQueryProductPage(Integer.valueOf(currentPage) - 2, 3);
				request.setAttribute("productList", productList);
				currentPage = String.valueOf((Integer.valueOf(currentPage) - 1));
				request.setAttribute("currentPage", currentPage);
				hs.setAttribute("currentPage", currentPage);
				request.getRequestDispatcher("WEB-INF/jsp/Product.jsp").forward(request, response);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{ //是第二页
			List<Product> productList = null;
			try {
				productList = productService.UserQueryProductPage(0, 3);
				request.setAttribute("productList", productList);
				request.getRequestDispatcher("WEB-INF/jsp/Product.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//用户搜索商品
	@RequestMapping("/userQueryProduct")
	public void userQueryProduct(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String producttext = request.getParameter("producttext");
		//将关键字存到Session
		HttpSession text = request.getSession();
		text.setAttribute("producttext", producttext);
		//System.out.println("producttext: " + producttext);
		int allPage = 0;
		try {
			List<Product> list = productService.UserFuzzyQueryProduct(producttext);
			//计算总页数
			int count = list.size();
			allPage = count / 3;
			if(count % 3 != 0){
				allPage = allPage + 1;
			}
			//将总页数及当前页数存在session域对象中
			HttpSession hs = request.getSession();
			hs.setAttribute("allPage", allPage);
			hs.setAttribute("currentPage", "1");
			//获取第一批3个商品对象
			List<Product> productList = productService.UserFuzzyQueryProductPage(producttext, 0, 3);
			request.setAttribute("productList", productList);
			request.setAttribute("producttext", producttext);
			request.getRequestDispatcher("WEB-INF/jsp/UserProductQuery.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//用户搜索商品下一页
	@RequestMapping("/userNextQueryProductPage")
	public void userNextQueryProductPage(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取上一页页码及总页码数
		HttpSession hs = request.getSession();
		String currentPage = (String) hs.getAttribute("currentPage");
		int allPage = (Integer) hs.getAttribute("allPage");
		//获取关键字
		HttpSession text = request.getSession();
		String producttext = (String) text.getAttribute("producttext");
		//System.out.println("producttext: " + producttext);
		//System.out.println("下一页: " + allPage + " : " + currentPage);
		//是否为倒数第二页
		if(Integer.valueOf(currentPage) < (allPage - 1)){ //不是倒数第二页
			try {
				List<Product> productList = productService.UserFuzzyQueryProductPage(producttext, Integer.valueOf(currentPage), 3);
				request.setAttribute("productList", productList);
				currentPage = String.valueOf((Integer.valueOf(currentPage) + 1));
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("producttext", producttext);
				hs.setAttribute("currentPage", currentPage);
				request.getRequestDispatcher("WEB-INF/jsp/UserProductQuery.jsp").forward(request, response);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{ //是倒数第二页
			List<Product> productList = null;
			try {
				int cp = Integer.valueOf(currentPage);
				if(cp >= allPage){
					cp = allPage - 1;
				}
				productList = productService.UserFuzzyQueryProductPage(producttext, cp, 3);
				request.setAttribute("productList", productList);
				currentPage = String.valueOf(allPage);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("producttext", producttext.toString());
				hs.setAttribute("currentPage", currentPage);
				request.setAttribute("nextPage", "尾页");
				request.getRequestDispatcher("WEB-INF/jsp/UserProductQuery.jsp").forward(request, response);		
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	//用户搜索商品上一页
	@RequestMapping("/userPreviousQueryProductPage")
	public void userPreviousQueryProductPage(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取下一页页码及总页码数
		HttpSession hs = request.getSession();
		String currentPage = (String) hs.getAttribute("currentPage");
		int allPage = (Integer) hs.getAttribute("allPage");
		//获取关键字
		HttpSession text = request.getSession();
		String producttext = (String) text.getAttribute("producttext");
		//System.out.println("producttext: " + producttext);
		//System.out.println("上一页: " + allPage + " : " + currentPage);
		//是否为第二页
		if(Integer.valueOf(currentPage) >= 2){ //不是第二页
			try {
				List<Product> productList = productService.UserFuzzyQueryProductPage(producttext, Integer.valueOf(currentPage) - 2, 3);
				request.setAttribute("productList", productList);
				currentPage = String.valueOf((Integer.valueOf(currentPage) - 1));
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("producttext", producttext.toString());
				hs.setAttribute("currentPage", currentPage);
				request.getRequestDispatcher("WEB-INF/jsp/UserProductQuery.jsp").forward(request, response);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{ //是第二页
			List<Product> productList = null;
			try {
				productList = productService.UserFuzzyQueryProductPage(producttext, 0, 3);
				request.setAttribute("productList", productList);
				request.setAttribute("producttext", producttext);
				request.getRequestDispatcher("WEB-INF/jsp/UserProductQuery.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//管理员跳转商品页面
	@RequestMapping("/adminSkipProduct")
	public void adminSkipProduct(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int allPage = 0;
		try {
			List<Product> list = productService.AdminQueryProduct();
			//计算总页数
			int count = list.size();
			allPage = count / 3;
			if(count % 3 != 0){
				allPage = allPage + 1;
			}
			//将总页数及当前页数存在session域对象中
			HttpSession hs = request.getSession();
			hs.setAttribute("allPage", allPage);
			hs.setAttribute("currentPage", "1");
			//获取第一批3个商品对象
			List<Product> productList = productService.AdminQueryProductPage(0, 3);
			request.setAttribute("productList", productList);
			request.getRequestDispatcher("WEB-INF/jsp/AdminManageProduct.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//管理员查看下一页商品
	@RequestMapping("/adminNextProductPage")
	public void adminNextProductPage(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取上一页页码及总页码数
		HttpSession hs = request.getSession();
		String currentPage = (String) hs.getAttribute("currentPage");
		int allPage = (Integer) hs.getAttribute("allPage");
		//System.out.println("下一页: " + allPage + " : " + currentPage);
		//是否为倒数第二页
		if(Integer.valueOf(currentPage) < (allPage - 1)){ //不是倒数第二页
			try {
				List<Product> productList = productService.AdminQueryProductPage(Integer.valueOf(currentPage), 3);
				request.setAttribute("productList", productList);
				currentPage = String.valueOf((Integer.valueOf(currentPage) + 1));
				request.setAttribute("currentPage", currentPage);
				hs.setAttribute("currentPage", currentPage);
				request.getRequestDispatcher("WEB-INF/jsp/AdminManageProduct.jsp").forward(request, response);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{ //是倒数第二页
			List<Product> productList = null;
			try {
				int cp = Integer.valueOf(currentPage);
				if(cp >= allPage){
					cp = allPage - 1;
				}
				productList = productService.AdminQueryProductPage(cp, 3);
				request.setAttribute("productList", productList);
				currentPage = String.valueOf(allPage);
				request.setAttribute("currentPage", currentPage);
				hs.setAttribute("currentPage", currentPage);
				request.setAttribute("nextPage", "尾页");
				request.getRequestDispatcher("WEB-INF/jsp/AdminManageProduct.jsp").forward(request, response);		
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//管理员查看上一页商品
	@RequestMapping("/adminPreviousProductPage")
	public void adminPreviousProductPage(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取下一页页码及总页码数
		HttpSession hs = request.getSession();
		String currentPage = (String) hs.getAttribute("currentPage");
		int allPage = (Integer) hs.getAttribute("allPage");
		//System.out.println("上一页: " + allPage + " : " + currentPage);
		//是否为第二页
		if(Integer.valueOf(currentPage) >= 2){ //不是第二页
			try {
				List<Product> productList = productService.AdminQueryProductPage(Integer.valueOf(currentPage) - 2, 3);
				request.setAttribute("productList", productList);
				currentPage = String.valueOf((Integer.valueOf(currentPage) - 1));
				request.setAttribute("currentPage", currentPage);
				hs.setAttribute("currentPage", currentPage);
				request.getRequestDispatcher("WEB-INF/jsp/AdminManageProduct.jsp").forward(request, response);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{ //是第二页
			List<Product> productList = null;
			try {
				productList = productService.AdminQueryProductPage(0, 3);
				request.setAttribute("productList", productList);
				request.getRequestDispatcher("WEB-INF/jsp/AdminManageProduct.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//管理员搜索商品
	@RequestMapping("/adminQueryProduct")
	public void adminQueryProduct(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String producttext = request.getParameter("producttext");
		//将关键字存到Session
		HttpSession text = request.getSession();
		text.setAttribute("producttext", producttext);
		//System.out.println("producttext: " + producttext);
		int allPage = 0;
		try {
			List<Product> list = productService.AdminFuzzyQueryProduct(producttext);
			//计算总页数
			int count = list.size();
			allPage = count / 3;
			if(count % 3 != 0){
				allPage = allPage + 1;
			}
			//将总页数及当前页数存在session域对象中
			HttpSession hs = request.getSession();
			hs.setAttribute("allPage", allPage);
			hs.setAttribute("currentPage", "1");
			//获取第一批3个商品对象
			List<Product> productList = productService.AdminFuzzyQueryProductPage(producttext, 0, 3);
			request.setAttribute("productList", productList);
			request.setAttribute("producttext", producttext);
			request.getRequestDispatcher("WEB-INF/jsp/AdminProductQuery.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//管理员搜索商品下一页
	@RequestMapping("/adminNextQueryProductPage")
	public void adminNextQueryProductPage(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取上一页页码及总页码数
		HttpSession hs = request.getSession();
		String currentPage = (String) hs.getAttribute("currentPage");
		int allPage = (Integer) hs.getAttribute("allPage");
		//获取关键字
		HttpSession text = request.getSession();
		String producttext = (String) text.getAttribute("producttext");
		//System.out.println("producttext: " + producttext);
		//System.out.println("下一页: " + allPage + " : " + currentPage);
		//是否为倒数第二页
		if(Integer.valueOf(currentPage) < (allPage - 1)){ //不是倒数第二页
			try {
				List<Product> productList = productService.AdminFuzzyQueryProductPage(producttext, Integer.valueOf(currentPage), 3);
				request.setAttribute("productList", productList);
				currentPage = String.valueOf((Integer.valueOf(currentPage) + 1));
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("producttext", producttext);
				hs.setAttribute("currentPage", currentPage);
				request.getRequestDispatcher("WEB-INF/jsp/AdminProductQuery.jsp").forward(request, response);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{ //是倒数第二页
			List<Product> productList = null;
			try {
				int cp = Integer.valueOf(currentPage);
				if(cp >= allPage){
					cp = allPage - 1;
				}
				productList = productService.AdminFuzzyQueryProductPage(producttext, cp, 3);
				request.setAttribute("productList", productList);
				currentPage = String.valueOf(allPage);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("producttext", producttext.toString());
				hs.setAttribute("currentPage", currentPage);
				request.setAttribute("nextPage", "尾页");
				request.getRequestDispatcher("WEB-INF/jsp/AdminProductQuery.jsp").forward(request, response);		
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	//管理员搜索商品上一页
	@RequestMapping("/adminPreviousQueryProductPage")
	public void adminPreviousQueryProductPage(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取下一页页码及总页码数
		HttpSession hs = request.getSession();
		String currentPage = (String) hs.getAttribute("currentPage");
		int allPage = (Integer) hs.getAttribute("allPage");
		//获取关键字
		HttpSession text = request.getSession();
		String producttext = (String) text.getAttribute("producttext");
		//System.out.println("producttext: " + producttext);
		//System.out.println("上一页: " + allPage + " : " + currentPage);
		//是否为第二页
		if(Integer.valueOf(currentPage) >= 2){ //不是第二页
			try {
				List<Product> productList = productService.AdminFuzzyQueryProductPage(producttext, Integer.valueOf(currentPage) - 2, 3);
				request.setAttribute("productList", productList);
				currentPage = String.valueOf((Integer.valueOf(currentPage) - 1));
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("producttext", producttext.toString());
				hs.setAttribute("currentPage", currentPage);
				request.getRequestDispatcher("WEB-INF/jsp/AdminProductQuery.jsp").forward(request, response);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{ //是第二页
			List<Product> productList = null;
			try {
				productList = productService.AdminFuzzyQueryProductPage(producttext, 0, 3);
				request.setAttribute("productList", productList);
				request.setAttribute("producttext", producttext);
				request.getRequestDispatcher("WEB-INF/jsp/AdminProductQuery.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//管理员跳转修改商品
	@RequestMapping("/adminSkipUpdateProduct")
	public void adminSkipUpdateProduct(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取页面数据
		String productId = request.getParameter("id");
		//System.out.println("skipId: " + productId);
		//商品id存到session中
		HttpSession hs = request.getSession();
		hs.setAttribute("productId", productId);
		request.setAttribute("id", productId);
		try {
			request.getRequestDispatcher("WEB-INF/jsp/AdminUpdateProduct.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//管理员修改商品
	@RequestMapping("/adminUpdateProduct")
	public void adminUpdateProduct(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取session的商品id
		HttpSession hs = request.getSession();
		String productId = (String) hs.getAttribute("productId");
		String productPrice = request.getParameter("price");
		//System.out.println("updateid: " + productId);
		//System.out.println("price: " + productPrice);
		//数据校验:正则表达式
		String priceRegex = "[1-9][0-9]{0,3}";
		boolean priceResult = productPrice.matches(priceRegex);
		//System.out.println("priceResult: " + priceResult);
		if(priceResult == true){
			try {
				productService.AdminUpdatePrice(Integer.valueOf(productPrice), Integer.valueOf(productId));
				String updateSuccess = "修改成功!";
				request.setAttribute("id", productId);
				request.setAttribute("updateSuccess", updateSuccess);
				request.getRequestDispatcher("WEB-INF/jsp/AdminUpdateProduct.jsp").forward(request, response);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				String updateError = "请正确输入价格!";
				request.setAttribute("id", productId);
				request.setAttribute("updateError", updateError);
				request.getRequestDispatcher("WEB-INF/jsp/AdminUpdateProduct.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//管理员下架商品
	@RequestMapping("/adminDeleteProduct")
	public void adminDeleteProduct(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取页面数据
		String productID = request.getParameter("id");
		//System.out.println("productID: " + productID);
		int allPage = 0;
		try {
			List<Product> list = productService.AdminQueryProduct();
			//计算总页数
			int count = list.size();
			allPage = count / 3;
			if(count % 3 != 0){
				allPage = allPage + 1;
			}
			//将总页数及当前页数存在session域对象中
			HttpSession hs = request.getSession();
			hs.setAttribute("allPage", allPage);
			hs.setAttribute("currentPage", "1");
			//修改状态
			productService.AdminDeleteProduct(Integer.valueOf(productID));
			//获取第一批3个商品对象
			List<Product> productList = productService.AdminQueryProductPage(0, 3);
			request.setAttribute("productList", productList);
			String success = "下架成功!";
			request.setAttribute("success", success);
			request.getRequestDispatcher("WEB-INF/jsp/AdminManageProduct.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//管理员恢复商品
	@RequestMapping("/adminRecoverProduct")
	public void adminRecoverProduct(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取页面数据
		String productID = request.getParameter("id");
		//System.out.println("productID: " + productID);
		int allPage = 0;
		try {
			List<Product> list = productService.AdminQueryProduct();
			//计算总页数
			int count = list.size();
			allPage = count / 3;
			if(count % 3 != 0){
				allPage = allPage + 1;
			}
			//将总页数及当前页数存在session域对象中
			HttpSession hs = request.getSession();
			hs.setAttribute("allPage", allPage);
			hs.setAttribute("currentPage", "1");
			//修改状态
			productService.AdminRecoverProduct(Integer.valueOf(productID));
			//获取第一批3个商品对象
			List<Product> productList = productService.AdminQueryProductPage(0, 3);
			request.setAttribute("productList", productList);
			String success = "重新上架成功!";
			request.setAttribute("success", success);
			request.getRequestDispatcher("WEB-INF/jsp/AdminManageProduct.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//管理员跳转添加商品
	@RequestMapping("/adminSkipAddProduct")
	public void adminSkipAddProduct(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		request.getRequestDispatcher("WEB-INF/jsp/AdminAddProduct.jsp").forward(request, response);
	}
	
	//管理员添加商品
	@RequestMapping("/adminAddProduct")
	public void adminAddProduct(@RequestParam("file") CommonsMultipartFile file, Product product, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String path = "D:\\WYCjsp\\picture\\";
		//获取当前时间
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH-mm-ss");
		String now = sdf.format(date);
		//获取源文件名称
		String fileName = file.getOriginalFilename();
		String[] str = fileName.split("\\.");
		String newFile = path + now + "." + str[1];
		File fPath = new File(newFile);
		String image = now + "." + str[1];
		try {
			file.transferTo(fPath);
			product.setImage(image);
			productService.AdminAddProduct(product);
			String addSuccess = "添加商品成功!";
			request.setAttribute("addSuccess", addSuccess);//携带数据
			request.getRequestDispatcher("WEB-INF/jsp/AdminAddProduct.jsp").forward(request, response);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
