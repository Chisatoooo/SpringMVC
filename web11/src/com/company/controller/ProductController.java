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
	
	//�û���ת��Ʒҳ��
	@RequestMapping("/userSkipProduct")
	public void userSkipProduct(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int allPage = 0;
		try {
			List<Product> list = productService.UserQueryProduct();
			//������ҳ��
			int count = list.size();
			allPage = count / 3;
			if(count % 3 != 0){
				allPage = allPage + 1;
			}
			//����ҳ������ǰҳ������session�������
			HttpSession hs = request.getSession();
			hs.setAttribute("allPage", allPage);
			hs.setAttribute("currentPage", "1");
			//��ȡ��һ��3����Ʒ����
			List<Product> productList = productService.UserQueryProductPage(0, 3);
			request.setAttribute("productList", productList);
			request.getRequestDispatcher("WEB-INF/jsp/Product.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//�û��鿴��һҳ��Ʒ
	@RequestMapping("/userNextProductPage")
	public void userNextProductPage(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//��ȡ��һҳҳ�뼰��ҳ����
		HttpSession hs = request.getSession();
		String currentPage = (String) hs.getAttribute("currentPage");
		int allPage = (Integer) hs.getAttribute("allPage");
		//System.out.println("��һҳ: " + allPage + " : " + currentPage);
		//�Ƿ�Ϊ�����ڶ�ҳ
		if(Integer.valueOf(currentPage) < (allPage - 1)){ //���ǵ����ڶ�ҳ
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
		}else{ //�ǵ����ڶ�ҳ
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
				request.setAttribute("nextPage", "βҳ");
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
	
	//�û��鿴��һҳ��Ʒ
	@RequestMapping("/userPreviousProductPage")
	public void userPreviousProductPage(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//��ȡ��һҳҳ�뼰��ҳ����
		HttpSession hs = request.getSession();
		String currentPage = (String) hs.getAttribute("currentPage");
		int allPage = (Integer) hs.getAttribute("allPage");
		//System.out.println("��һҳ: " + allPage + " : " + currentPage);
		//�Ƿ�Ϊ�ڶ�ҳ
		if(Integer.valueOf(currentPage) >= 2){ //���ǵڶ�ҳ
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
		}else{ //�ǵڶ�ҳ
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
	
	//�û�������Ʒ
	@RequestMapping("/userQueryProduct")
	public void userQueryProduct(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String producttext = request.getParameter("producttext");
		//���ؼ��ִ浽Session
		HttpSession text = request.getSession();
		text.setAttribute("producttext", producttext);
		//System.out.println("producttext: " + producttext);
		int allPage = 0;
		try {
			List<Product> list = productService.UserFuzzyQueryProduct(producttext);
			//������ҳ��
			int count = list.size();
			allPage = count / 3;
			if(count % 3 != 0){
				allPage = allPage + 1;
			}
			//����ҳ������ǰҳ������session�������
			HttpSession hs = request.getSession();
			hs.setAttribute("allPage", allPage);
			hs.setAttribute("currentPage", "1");
			//��ȡ��һ��3����Ʒ����
			List<Product> productList = productService.UserFuzzyQueryProductPage(producttext, 0, 3);
			request.setAttribute("productList", productList);
			request.setAttribute("producttext", producttext);
			request.getRequestDispatcher("WEB-INF/jsp/UserProductQuery.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//�û�������Ʒ��һҳ
	@RequestMapping("/userNextQueryProductPage")
	public void userNextQueryProductPage(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//��ȡ��һҳҳ�뼰��ҳ����
		HttpSession hs = request.getSession();
		String currentPage = (String) hs.getAttribute("currentPage");
		int allPage = (Integer) hs.getAttribute("allPage");
		//��ȡ�ؼ���
		HttpSession text = request.getSession();
		String producttext = (String) text.getAttribute("producttext");
		//System.out.println("producttext: " + producttext);
		//System.out.println("��һҳ: " + allPage + " : " + currentPage);
		//�Ƿ�Ϊ�����ڶ�ҳ
		if(Integer.valueOf(currentPage) < (allPage - 1)){ //���ǵ����ڶ�ҳ
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
		}else{ //�ǵ����ڶ�ҳ
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
				request.setAttribute("nextPage", "βҳ");
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
	
	//�û�������Ʒ��һҳ
	@RequestMapping("/userPreviousQueryProductPage")
	public void userPreviousQueryProductPage(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//��ȡ��һҳҳ�뼰��ҳ����
		HttpSession hs = request.getSession();
		String currentPage = (String) hs.getAttribute("currentPage");
		int allPage = (Integer) hs.getAttribute("allPage");
		//��ȡ�ؼ���
		HttpSession text = request.getSession();
		String producttext = (String) text.getAttribute("producttext");
		//System.out.println("producttext: " + producttext);
		//System.out.println("��һҳ: " + allPage + " : " + currentPage);
		//�Ƿ�Ϊ�ڶ�ҳ
		if(Integer.valueOf(currentPage) >= 2){ //���ǵڶ�ҳ
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
		}else{ //�ǵڶ�ҳ
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
	
	//����Ա��ת��Ʒҳ��
	@RequestMapping("/adminSkipProduct")
	public void adminSkipProduct(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int allPage = 0;
		try {
			List<Product> list = productService.AdminQueryProduct();
			//������ҳ��
			int count = list.size();
			allPage = count / 3;
			if(count % 3 != 0){
				allPage = allPage + 1;
			}
			//����ҳ������ǰҳ������session�������
			HttpSession hs = request.getSession();
			hs.setAttribute("allPage", allPage);
			hs.setAttribute("currentPage", "1");
			//��ȡ��һ��3����Ʒ����
			List<Product> productList = productService.AdminQueryProductPage(0, 3);
			request.setAttribute("productList", productList);
			request.getRequestDispatcher("WEB-INF/jsp/AdminManageProduct.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//����Ա�鿴��һҳ��Ʒ
	@RequestMapping("/adminNextProductPage")
	public void adminNextProductPage(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//��ȡ��һҳҳ�뼰��ҳ����
		HttpSession hs = request.getSession();
		String currentPage = (String) hs.getAttribute("currentPage");
		int allPage = (Integer) hs.getAttribute("allPage");
		//System.out.println("��һҳ: " + allPage + " : " + currentPage);
		//�Ƿ�Ϊ�����ڶ�ҳ
		if(Integer.valueOf(currentPage) < (allPage - 1)){ //���ǵ����ڶ�ҳ
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
		}else{ //�ǵ����ڶ�ҳ
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
				request.setAttribute("nextPage", "βҳ");
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
	
	//����Ա�鿴��һҳ��Ʒ
	@RequestMapping("/adminPreviousProductPage")
	public void adminPreviousProductPage(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//��ȡ��һҳҳ�뼰��ҳ����
		HttpSession hs = request.getSession();
		String currentPage = (String) hs.getAttribute("currentPage");
		int allPage = (Integer) hs.getAttribute("allPage");
		//System.out.println("��һҳ: " + allPage + " : " + currentPage);
		//�Ƿ�Ϊ�ڶ�ҳ
		if(Integer.valueOf(currentPage) >= 2){ //���ǵڶ�ҳ
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
		}else{ //�ǵڶ�ҳ
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
	
	//����Ա������Ʒ
	@RequestMapping("/adminQueryProduct")
	public void adminQueryProduct(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String producttext = request.getParameter("producttext");
		//���ؼ��ִ浽Session
		HttpSession text = request.getSession();
		text.setAttribute("producttext", producttext);
		//System.out.println("producttext: " + producttext);
		int allPage = 0;
		try {
			List<Product> list = productService.AdminFuzzyQueryProduct(producttext);
			//������ҳ��
			int count = list.size();
			allPage = count / 3;
			if(count % 3 != 0){
				allPage = allPage + 1;
			}
			//����ҳ������ǰҳ������session�������
			HttpSession hs = request.getSession();
			hs.setAttribute("allPage", allPage);
			hs.setAttribute("currentPage", "1");
			//��ȡ��һ��3����Ʒ����
			List<Product> productList = productService.AdminFuzzyQueryProductPage(producttext, 0, 3);
			request.setAttribute("productList", productList);
			request.setAttribute("producttext", producttext);
			request.getRequestDispatcher("WEB-INF/jsp/AdminProductQuery.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//����Ա������Ʒ��һҳ
	@RequestMapping("/adminNextQueryProductPage")
	public void adminNextQueryProductPage(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//��ȡ��һҳҳ�뼰��ҳ����
		HttpSession hs = request.getSession();
		String currentPage = (String) hs.getAttribute("currentPage");
		int allPage = (Integer) hs.getAttribute("allPage");
		//��ȡ�ؼ���
		HttpSession text = request.getSession();
		String producttext = (String) text.getAttribute("producttext");
		//System.out.println("producttext: " + producttext);
		//System.out.println("��һҳ: " + allPage + " : " + currentPage);
		//�Ƿ�Ϊ�����ڶ�ҳ
		if(Integer.valueOf(currentPage) < (allPage - 1)){ //���ǵ����ڶ�ҳ
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
		}else{ //�ǵ����ڶ�ҳ
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
				request.setAttribute("nextPage", "βҳ");
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
	
	//����Ա������Ʒ��һҳ
	@RequestMapping("/adminPreviousQueryProductPage")
	public void adminPreviousQueryProductPage(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//��ȡ��һҳҳ�뼰��ҳ����
		HttpSession hs = request.getSession();
		String currentPage = (String) hs.getAttribute("currentPage");
		int allPage = (Integer) hs.getAttribute("allPage");
		//��ȡ�ؼ���
		HttpSession text = request.getSession();
		String producttext = (String) text.getAttribute("producttext");
		//System.out.println("producttext: " + producttext);
		//System.out.println("��һҳ: " + allPage + " : " + currentPage);
		//�Ƿ�Ϊ�ڶ�ҳ
		if(Integer.valueOf(currentPage) >= 2){ //���ǵڶ�ҳ
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
		}else{ //�ǵڶ�ҳ
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
	
	//����Ա��ת�޸���Ʒ
	@RequestMapping("/adminSkipUpdateProduct")
	public void adminSkipUpdateProduct(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//��ȡҳ������
		String productId = request.getParameter("id");
		//System.out.println("skipId: " + productId);
		//��Ʒid�浽session��
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
	
	//����Ա�޸���Ʒ
	@RequestMapping("/adminUpdateProduct")
	public void adminUpdateProduct(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//��ȡsession����Ʒid
		HttpSession hs = request.getSession();
		String productId = (String) hs.getAttribute("productId");
		String productPrice = request.getParameter("price");
		//System.out.println("updateid: " + productId);
		//System.out.println("price: " + productPrice);
		//����У��:������ʽ
		String priceRegex = "[1-9][0-9]{0,3}";
		boolean priceResult = productPrice.matches(priceRegex);
		//System.out.println("priceResult: " + priceResult);
		if(priceResult == true){
			try {
				productService.AdminUpdatePrice(Integer.valueOf(productPrice), Integer.valueOf(productId));
				String updateSuccess = "�޸ĳɹ�!";
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
				String updateError = "����ȷ����۸�!";
				request.setAttribute("id", productId);
				request.setAttribute("updateError", updateError);
				request.getRequestDispatcher("WEB-INF/jsp/AdminUpdateProduct.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//����Ա�¼���Ʒ
	@RequestMapping("/adminDeleteProduct")
	public void adminDeleteProduct(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//��ȡҳ������
		String productID = request.getParameter("id");
		//System.out.println("productID: " + productID);
		int allPage = 0;
		try {
			List<Product> list = productService.AdminQueryProduct();
			//������ҳ��
			int count = list.size();
			allPage = count / 3;
			if(count % 3 != 0){
				allPage = allPage + 1;
			}
			//����ҳ������ǰҳ������session�������
			HttpSession hs = request.getSession();
			hs.setAttribute("allPage", allPage);
			hs.setAttribute("currentPage", "1");
			//�޸�״̬
			productService.AdminDeleteProduct(Integer.valueOf(productID));
			//��ȡ��һ��3����Ʒ����
			List<Product> productList = productService.AdminQueryProductPage(0, 3);
			request.setAttribute("productList", productList);
			String success = "�¼ܳɹ�!";
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
	
	//����Ա�ָ���Ʒ
	@RequestMapping("/adminRecoverProduct")
	public void adminRecoverProduct(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//��ȡҳ������
		String productID = request.getParameter("id");
		//System.out.println("productID: " + productID);
		int allPage = 0;
		try {
			List<Product> list = productService.AdminQueryProduct();
			//������ҳ��
			int count = list.size();
			allPage = count / 3;
			if(count % 3 != 0){
				allPage = allPage + 1;
			}
			//����ҳ������ǰҳ������session�������
			HttpSession hs = request.getSession();
			hs.setAttribute("allPage", allPage);
			hs.setAttribute("currentPage", "1");
			//�޸�״̬
			productService.AdminRecoverProduct(Integer.valueOf(productID));
			//��ȡ��һ��3����Ʒ����
			List<Product> productList = productService.AdminQueryProductPage(0, 3);
			request.setAttribute("productList", productList);
			String success = "�����ϼܳɹ�!";
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
	
	//����Ա��ת�����Ʒ
	@RequestMapping("/adminSkipAddProduct")
	public void adminSkipAddProduct(Product product, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		request.getRequestDispatcher("WEB-INF/jsp/AdminAddProduct.jsp").forward(request, response);
	}
	
	//����Ա�����Ʒ
	@RequestMapping("/adminAddProduct")
	public void adminAddProduct(@RequestParam("file") CommonsMultipartFile file, Product product, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String path = "D:\\WYCjsp\\picture\\";
		//��ȡ��ǰʱ��
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH-mm-ss");
		String now = sdf.format(date);
		//��ȡԴ�ļ�����
		String fileName = file.getOriginalFilename();
		String[] str = fileName.split("\\.");
		String newFile = path + now + "." + str[1];
		File fPath = new File(newFile);
		String image = now + "." + str[1];
		try {
			file.transferTo(fPath);
			product.setImage(image);
			productService.AdminAddProduct(product);
			String addSuccess = "�����Ʒ�ɹ�!";
			request.setAttribute("addSuccess", addSuccess);//Я������
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
