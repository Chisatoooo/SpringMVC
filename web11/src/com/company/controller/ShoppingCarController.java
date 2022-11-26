package com.company.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.entity.Admin;
import com.company.entity.ShoppingCar;
import com.company.service.ShoppingCarService;

@Controller
public class ShoppingCarController {
	@Autowired
	private ShoppingCarService shoppingCarService;
	
	//�û���ӹ��ﳵ
	@RequestMapping("/addShoppingCar")
	public void addShoppingCar(ShoppingCar shoppingCar, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//��ȡSession���û���
		HttpSession hs = request.getSession();
		String login = (String) hs.getAttribute("Login");
		//System.out.println("addlogin:" + login);
		if(login == null){
			request.getRequestDispatcher("UserLogin.jsp").forward(request, response);
		}else{
			String username = request.getParameter("username");
			String productID = request.getParameter("id");
			String price = request.getParameter("price");
			String name = request.getParameter("name");
//			System.out.println("username: " + username);
//			System.out.println("productID: " + productID);
//			System.out.println("price: " + price);
//			System.out.println("name: " + name);
			int number = 1;
			//��װ����
			shoppingCar.setFk_username(username);
			shoppingCar.setFk_id(Integer.valueOf(productID));
			shoppingCar.setNumber(number);
			shoppingCar.setProductname(name);
			shoppingCar.setPrice(Integer.valueOf(price));
			shoppingCar.setSumprice(number * Integer.valueOf(price));
			try {
				List<Integer> list = shoppingCarService.selectFkId(username);
				if(list.contains(Integer.valueOf(productID))){
					int num = shoppingCarService.selectNumber(Integer.valueOf(productID), username);
					num = num + 1;
					shoppingCarService.updateNumber(num, Integer.valueOf(productID), username);
					shoppingCarService.updateSumprice(num * Integer.valueOf(price), Integer.valueOf(productID), username);
					List<Integer> list2 = new ArrayList<Integer>();
					List<ShoppingCar> shoppingCarList = shoppingCarService.queryShoppingCar(username);
					for(ShoppingCar s : shoppingCarList){
						list2.add(s.getFk_id());
					}
					//System.out.println("skip2:" + list2);
					//�浽Session
					HttpSession hs2 = request.getSession();
					hs2.setAttribute("list2", list2);
					request.setAttribute("shoppingCarList", shoppingCarList);
					String success = "�Ѽ��빺�ﳵ!";
					request.setAttribute("success", success);
					request.setAttribute("username", username);
					request.getRequestDispatcher("WEB-INF/jsp/ShoppingCar.jsp").forward(request, response);
				}else{
					shoppingCarService.insert(shoppingCar);
					List<Integer> list2 = new ArrayList<Integer>();
					List<ShoppingCar> shoppingCarList = shoppingCarService.queryShoppingCar(username);
					for(ShoppingCar s : shoppingCarList){
						list2.add(s.getFk_id());
					}
					//System.out.println("skip2:" + list2);
					//�浽Session
					HttpSession hs2 = request.getSession();
					hs2.setAttribute("list2", list2);
					
					request.setAttribute("shoppingCarList", shoppingCarList);
					String success = "�Ѽ��빺�ﳵ!";
					request.setAttribute("success", success);
					request.setAttribute("username", username);
					request.getRequestDispatcher("WEB-INF/jsp/ShoppingCar.jsp").forward(request, response);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//�û�ɾ�����ﳵ��Ʒ
	@RequestMapping("/deleteShoppingCar")
	public void deleteShoppingCar(ShoppingCar shoppingCar, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String fkId = request.getParameter("fkId");
		String username = request.getParameter("username");
		//System.out.println(fkId);
		//System.out.println(username);
		try {
			//ɾ�����ﳵ�е���Ʒ
			shoppingCarService.delete(Integer.valueOf(fkId), username);
			List<ShoppingCar> shoppingCarList = shoppingCarService.queryShoppingCar(username);
			String success = "ɾ���ɹ�!";
			request.setAttribute("success", success);
			request.setAttribute("username", username); 
			request.setAttribute("shoppingCarList", shoppingCarList); 
			request.getRequestDispatcher("WEB-INF/jsp/ShoppingCar.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//�û���ת���ﳵ
	@RequestMapping("/userSkipShoppingCar")
	public void userSkipShoppingCar(ShoppingCar shoppingCar, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//��ȡSession���û���
		HttpSession hs2 = request.getSession();
		String login = (String) hs2.getAttribute("Login");
		//System.out.println("skiplogin: " + login);
		if(login == null){
			request.getRequestDispatcher("UserLogin.jsp").forward(request, response);
		}else{
			try {
				List<Integer> list2 = new ArrayList<Integer>();
				List<ShoppingCar> shoppingCarList = shoppingCarService.queryShoppingCar(login);
				for(ShoppingCar s : shoppingCarList){
					list2.add(s.getFk_id());
				}
				//System.out.println("skip:" + list2);
				//�浽Session
				HttpSession hs = request.getSession();
				hs.setAttribute("list2", list2);
				
				request.setAttribute("shoppingCarList", shoppingCarList);
				request.setAttribute("username", login);
				request.getRequestDispatcher("WEB-INF/jsp/ShoppingCar.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
