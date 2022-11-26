package com.company.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.company.entity.Admin;
import com.company.entity.Orders;
import com.company.entity.ShoppingCar;
import com.company.service.OrdersService;
import com.company.service.ShoppingCarService;
import com.company.service.UserService;

@Controller
public class OrdersController {
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private UserService userService;
	@Autowired
	private ShoppingCarService shoppingCarService;
	
	//购买单个商品
	@RequestMapping("/buyProduct")
	public void buyProduct(Orders orders, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String fkId = request.getParameter("fkId");
		String username = request.getParameter("username");
		String sumprice = request.getParameter("sumprice");
		String productname = request.getParameter("productname");
		try {
			//获取用户的用户手机号和地址
			String address = userService.queryAddress(username);
			String phone = userService.queryPhone(username);
			if(address.equals("无信息")){
				String result = "地址未填写, 请先完善信息!";
				List<ShoppingCar> shoppingCarList = shoppingCarService.queryShoppingCar(username);
				request.setAttribute("result", result);
				request.setAttribute("success", "");
				request.setAttribute("shoppingCarList", shoppingCarList);
				request.setAttribute("username", username);
				request.getRequestDispatcher("WEB-INF/jsp/ShoppingCar.jsp").forward(request, response);
			}else{
				orders.setFk_order_username(username);
				orders.setFk_address(address);
				orders.setFk_phone(phone);
				orders.setFk_product_id(Integer.valueOf(fkId));
				//加入订单表中
				ordersService.insert(orders);
				//删除购物车中的商品
				shoppingCarService.delete(Integer.valueOf(fkId), username);
				request.setAttribute("sumprice", sumprice);
				request.setAttribute("productname", productname);
				request.getRequestDispatcher("WEB-INF/jsp/alipay.trade.page.pay.jsp").forward(request, response);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//购买一键购买商品
	@RequestMapping("/buyAllProduct")
	public void buyAllProduct(Orders orders, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String sumprice = request.getParameter("sumprice");
		String productname = request.getParameter("productname");
		//System.out.println("sumprice:" + sumprice);
		HttpSession hs = request.getSession();
		List<Integer> list = (List<Integer>) hs.getAttribute("list2");
		//将用户名存到Session
		HttpSession hs2 = request.getSession();
		String login = (String) hs2.getAttribute("Login");
		if(login == null){
			request.getRequestDispatcher("UserLogin.jsp").forward(request, response);
		}else{
			for(int index : list){
				try {
					//获取用户的用户手机号和用户地址
					String address = userService.queryAddress(login);
					String phone = userService.queryPhone(login);
					if(address.equals("无信息")){
						String result = "地址未填写, 请先完善信息!";
						List<ShoppingCar> shoppingCarList = shoppingCarService.queryShoppingCar(login);
						request.setAttribute("result", result);
						request.setAttribute("success", "");
						request.setAttribute("shoppingCarList", shoppingCarList);
						request.setAttribute("username", login);
						request.getRequestDispatcher("WEB-INF/jsp/ShoppingCar.jsp").forward(request, response);
						return;
					}else{
						orders.setFk_order_username(login);
						orders.setFk_phone(phone);
						orders.setFk_address(address);
						orders.setFk_product_id(Integer.valueOf(index));
						//加入订单表中
						ordersService.insert(orders);
						//删除购物车中的商品
						shoppingCarService.delete(Integer.valueOf(index), login);
						request.setAttribute("sumprice", sumprice);
						request.setAttribute("productname", productname);
						request.getRequestDispatcher("WEB-INF/jsp/alipay.trade.page.pay.jsp").forward(request, response);
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
	
	//用户跳转查看订单
	@RequestMapping("/userSkipOrders")
	public void userSkipOrders(Orders orders, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取Session的用户名
		HttpSession hs = request.getSession();
		String login = (String) hs.getAttribute("Login");
		//System.out.println("orderslogin:" + login);
		try {
			List<Orders> ordersList = ordersService.UserQueryOrders(login);
			request.setAttribute("ordersList", ordersList);
			request.setAttribute("username", login);
			request.getRequestDispatcher("WEB-INF/jsp/UserOrders.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//管理员跳转查看订单
	@RequestMapping("/adminSkipOrders")
	public void adminSkipOrders(Orders orders, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		try {
			List<Orders> ordersList = ordersService.AdminQueryOrders();
			request.setAttribute("ordersList", ordersList);
			request.getRequestDispatcher("WEB-INF/jsp/AdminManageOrders.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//管理员修改发货状态
	@RequestMapping("/adminUpdateOrders")
	public void adminUpdateOrders(Orders orders, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取页面数据
		String oid = request.getParameter("oid");
		try {
			ordersService.updateDeliverystate(Integer.valueOf(oid));
			//跳转页面
			List<Orders> ordersList = ordersService.AdminQueryOrders();
			String success = "发货状态已修改!";
			request.setAttribute("ordersList", ordersList);
			request.setAttribute("success", success);
			request.getRequestDispatcher("WEB-INF/jsp/AdminManageOrders.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//管理员取消订单
	@RequestMapping("/adminCancelOrders")
	public void adminCancelOrders(Orders orders, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取页面数据
		String oid = request.getParameter("oid");
		try {
			//修改状态
			ordersService.cancelOrder(Integer.valueOf(oid));
			//跳转页面
			List<Orders> ordersList = ordersService.AdminQueryOrders();
			String success = "订单已取消!";
			request.setAttribute("ordersList", ordersList);
			request.setAttribute("success", success);
			request.getRequestDispatcher("WEB-INF/jsp/AdminManageOrders.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
