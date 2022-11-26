package com.company.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.entity.Admin;
import com.company.entity.User;
import com.company.service.AdminService;
import com.company.service.UserService;

@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;
	
	//管理员登录
	@RequestMapping("/adminLogin")
	public void adminLogin(Admin admin, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取页面数据
		String adminname = admin.getAdminname();
		//System.out.println("adminname: " + adminname);
		String adminpwd = admin.getAdminpwd();
		//System.out.println("adminpwd: " + adminpwd);
		try {
			if(adminname == ""){
				String result = "0";
				//写到前端
		        PrintWriter pw = response.getWriter();
		        pw.print(result);
			}else if(adminpwd == ""){
				String result = "00";
				//写到前端
		        PrintWriter pw = response.getWriter();
		        pw.print(result);
			}else{
				String pwd = adminService.adminSelectPwd(adminname);
				if(pwd == null){
					String result = "3";
					//写到前端
			        PrintWriter pw = response.getWriter();
			        pw.print(result);
				}else{
					if(pwd.equals(adminpwd)){//密码正确
						String result = "1";
						//写到前端
				        PrintWriter pw = response.getWriter();
						pw.print(result);
					}else{//密码错误
						String result = "2";
						//写到前端
				        PrintWriter pw = response.getWriter();
						pw.print(result);
					}
				}
			}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
	
	//跳转管理登录成功
	@RequestMapping("/adminLoginSkip")
	public void adminLoginSkip(Admin admin, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		try {
			//获取页面数据
			String adminname = request.getParameter("adminname");
			request.setAttribute("adminname", adminname);
			//System.out.println("adminname: " + adminname);
			request.getRequestDispatcher("WEB-INF/jsp/AdminLoginSuccess.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//管理员查看用户
	@RequestMapping("/adminQueryAllUser")
	public void adminQueryAllUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int allPage = 0;
		try {
			List<User> list = userService.AdminSelectAllUser();
			//计算总页数
			int count = list.size();
			allPage = count / 5;
			if(count % 5 != 0){
				allPage = allPage + 1;
			}
			//将总页数及当前页数存在session域对象中
			HttpSession hs = request.getSession();
			hs.setAttribute("allPage", allPage);
			hs.setAttribute("currentPage", "1");
			//获取第一批5个用户对象
			List<User> userList = userService.AdminSelectPageUser(0, 5);
			request.setAttribute("userList", userList);
			request.getRequestDispatcher("WEB-INF/jsp/AdminManageUsers.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//管理员查看用户下一页
	@RequestMapping("/userNextPage")
	public void userNextPage(HttpServletRequest request, HttpServletResponse response) throws IOException{
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
				List<User> userList = userService.AdminSelectPageUser(Integer.valueOf(currentPage), 5);
				request.setAttribute("userList", userList);
				currentPage = String.valueOf((Integer.valueOf(currentPage) + 1));
				request.setAttribute("currentPage", currentPage);
				hs.setAttribute("currentPage", currentPage);
				request.getRequestDispatcher("WEB-INF/jsp/AdminManageUsers.jsp").forward(request, response);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{ //是倒数第二页
			List<User> userList = null;
			try {
				int cp = Integer.valueOf(currentPage);
				if(cp >= allPage){
					cp = allPage - 1;
				}
				userList = userService.AdminSelectPageUser(cp, 5);
				request.setAttribute("userList", userList);
				currentPage = String.valueOf(allPage);
				request.setAttribute("currentPage", currentPage);
				hs.setAttribute("currentPage", currentPage);
				request.setAttribute("nextPage", "尾页");
				request.getRequestDispatcher("WEB-INF/jsp/AdminManageUsers.jsp").forward(request, response);		
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//管理员查看用户上一页
	@RequestMapping("/userPreviousPage")
	public void userPreviousPage(HttpServletRequest request, HttpServletResponse response) throws IOException{
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
				List<User> userList = userService.AdminSelectPageUser(Integer.valueOf(currentPage) - 2, 5);
				request.setAttribute("userList", userList);
				currentPage = String.valueOf((Integer.valueOf(currentPage) - 1));
				request.setAttribute("currentPage", currentPage);
				hs.setAttribute("currentPage", currentPage);
				request.getRequestDispatcher("WEB-INF/jsp/AdminManageUsers.jsp").forward(request, response);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{ //是第二页
			List<User> userList = null;
			try {
				userList = userService.AdminSelectPageUser(0, 5);
				request.setAttribute("userList", userList);
				request.getRequestDispatcher("WEB-INF/jsp/AdminManageUsers.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//管理员跳转修改用户
	@RequestMapping("/adminSkipUpdateUser")
	public void adminSkipUpdateUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");
		//System.out.println("username: " + username);
		try {
			request.setAttribute("username", username);
			request.getRequestDispatcher("WEB-INF/jsp/AdminUpdateInformation.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//管理员修改用户
	@RequestMapping("/adminUpdateUser")
	public void adminUpdateUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取页面数据
		String username = request.getParameter("username");
		String userpwd = request.getParameter("userpwd");
		String phone = request.getParameter("phone");
		String birthday = request.getParameter("birthday");
		String address = request.getParameter("address");
		//数据校验:正则表达式
		String userpwdRegex = "[a-zA-Z0-9]{8,20}"; //8-20位
		String phoneRegex = "[1][3,4,5,7,8][0-9]{9}";
		String birthdayRegex = "^((?:19[2-9]\\d{1})|(?:20(?:(?:0[0-9])|(?:1[0-8]))))\\-((?:0?[1-9])|(?:1[0-2]))\\-((?:0?[1-9])|(?:[1-2][0-9])|30|31)$";
		boolean userpwdResult = userpwd.matches(userpwdRegex);
		System.out.println("userpwdResult: " + userpwdResult);
		boolean birthdayResult = birthday.matches(birthdayRegex);
		System.out.println("birthdayResult: " + birthdayResult);
		boolean phoneResult = phone.matches(phoneRegex);
		System.out.println("phoneResult: " + phoneResult);
		if(userpwdResult == true && birthdayResult == true && phoneResult == true){
			try {
				userService.UserUpdatePwd(userpwd, username);
				userService.UserUpdateBirthday(birthday, username);
				userService.UserUpdateAddress(address, username);
				userService.UserUpdatePhone(phone, username);
				String updateSuccess = "修改成功!";
				/*
				String headimage = null;
				String imagePath = "D:/WYCjsp/headimage/" + username + ".jpg";
			    File file = new File(imagePath);
				if(!file.exists()) {
					headimage = "default.jpg";
					//System.out.println("不存在");
				}else{
					headimage = username + ".jpg";
					//System.out.println("存在");
				}
				*/
				request.setAttribute("username", username);
				//request.setAttribute("headimage", headimage);//携带数据
				request.setAttribute("updateSuccess", updateSuccess);//携带数据
				//跳转到成功页面
				request.getRequestDispatcher("WEB-INF/jsp/AdminUpdateInformation.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			String updateError = "数据不符合要求!";
			/*
			String headimage = null;
			String imagePath = "D:/WYCjsp/headimage/" + username + ".jpg";
		    File file = new File(imagePath);
			if(!file.exists()) {
				headimage = "default.jpg";
				//System.out.println("不存在");
			}else{
				headimage = username + ".jpg";
				//System.out.println("存在");
			}
			*/
			//request.setAttribute("headimage", headimage);//携带数据
			try {
				request.setAttribute("username", username);
				request.setAttribute("updateError", updateError);
				request.getRequestDispatcher("WEB-INF/jsp/AdminUpdateInformation.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//管理员注销用户
	@RequestMapping("/adminDeleteUser")
	public void adminDeleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取页面数据
		String username = request.getParameter("username");
		//System.out.println(username);
		//String current = request.getParameter("currentPage");
		//System.out.println("current:" + current);
		int allPage = 0;
		try {
			List<User> list = userService.AdminSelectAllUser();
			//计算总页数
			int count = list.size();
			allPage = count / 5;
			if(count % 5 != 0){
				allPage = allPage + 1;
			}
			//将总页数及当前页数存在session域对象中
			HttpSession hs = request.getSession();
			hs.setAttribute("allPage", allPage);
			hs.setAttribute("currentPage", "1");
			//修改状态
			userService.AdminDeleteUser(username);
			//获取当前页5个用户对象
			List<User> userList = userService.AdminSelectPageUser(0, 5);
			request.setAttribute("userList", userList);
			String success = "用户注销成功!";
			request.setAttribute("success", success);
			request.getRequestDispatcher("WEB-INF/jsp/AdminManageUsers.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//管理员恢复用户
	@RequestMapping("/adminRecoverUser")
	public void adminRecoverUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取页面数据
		String username = request.getParameter("username");
		//System.out.println(username);
		//String current = request.getParameter("currentPage");
		//System.out.println("current:" + current);
		int allPage = 0;
		try {
			List<User> list = userService.AdminSelectAllUser();
			//计算总页数
			int count = list.size();
			allPage = count / 5;
			if(count % 5 != 0){
				allPage = allPage + 1;
			}
			//将总页数及当前页数存在session域对象中
			HttpSession hs = request.getSession();
			hs.setAttribute("allPage", allPage);
			hs.setAttribute("currentPage", "1");
			//修改状态
			userService.AdminRecoverUser(username);
			//获取当前页5个用户对象
			List<User> userList = userService.AdminSelectPageUser(0, 5);
			request.setAttribute("userList", userList);
			String success = "用户恢复成功!";
			request.setAttribute("success", success);
			request.getRequestDispatcher("WEB-INF/jsp/AdminManageUsers.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
