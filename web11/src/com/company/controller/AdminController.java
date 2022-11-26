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
	
	//����Ա��¼
	@RequestMapping("/adminLogin")
	public void adminLogin(Admin admin, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//��ȡҳ������
		String adminname = admin.getAdminname();
		//System.out.println("adminname: " + adminname);
		String adminpwd = admin.getAdminpwd();
		//System.out.println("adminpwd: " + adminpwd);
		try {
			if(adminname == ""){
				String result = "0";
				//д��ǰ��
		        PrintWriter pw = response.getWriter();
		        pw.print(result);
			}else if(adminpwd == ""){
				String result = "00";
				//д��ǰ��
		        PrintWriter pw = response.getWriter();
		        pw.print(result);
			}else{
				String pwd = adminService.adminSelectPwd(adminname);
				if(pwd == null){
					String result = "3";
					//д��ǰ��
			        PrintWriter pw = response.getWriter();
			        pw.print(result);
				}else{
					if(pwd.equals(adminpwd)){//������ȷ
						String result = "1";
						//д��ǰ��
				        PrintWriter pw = response.getWriter();
						pw.print(result);
					}else{//�������
						String result = "2";
						//д��ǰ��
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
	
	//��ת�����¼�ɹ�
	@RequestMapping("/adminLoginSkip")
	public void adminLoginSkip(Admin admin, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		try {
			//��ȡҳ������
			String adminname = request.getParameter("adminname");
			request.setAttribute("adminname", adminname);
			//System.out.println("adminname: " + adminname);
			request.getRequestDispatcher("WEB-INF/jsp/AdminLoginSuccess.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//����Ա�鿴�û�
	@RequestMapping("/adminQueryAllUser")
	public void adminQueryAllUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int allPage = 0;
		try {
			List<User> list = userService.AdminSelectAllUser();
			//������ҳ��
			int count = list.size();
			allPage = count / 5;
			if(count % 5 != 0){
				allPage = allPage + 1;
			}
			//����ҳ������ǰҳ������session�������
			HttpSession hs = request.getSession();
			hs.setAttribute("allPage", allPage);
			hs.setAttribute("currentPage", "1");
			//��ȡ��һ��5���û�����
			List<User> userList = userService.AdminSelectPageUser(0, 5);
			request.setAttribute("userList", userList);
			request.getRequestDispatcher("WEB-INF/jsp/AdminManageUsers.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//����Ա�鿴�û���һҳ
	@RequestMapping("/userNextPage")
	public void userNextPage(HttpServletRequest request, HttpServletResponse response) throws IOException{
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
		}else{ //�ǵ����ڶ�ҳ
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
				request.setAttribute("nextPage", "βҳ");
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
	
	//����Ա�鿴�û���һҳ
	@RequestMapping("/userPreviousPage")
	public void userPreviousPage(HttpServletRequest request, HttpServletResponse response) throws IOException{
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
		}else{ //�ǵڶ�ҳ
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
	
	//����Ա��ת�޸��û�
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
	
	//����Ա�޸��û�
	@RequestMapping("/adminUpdateUser")
	public void adminUpdateUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//��ȡҳ������
		String username = request.getParameter("username");
		String userpwd = request.getParameter("userpwd");
		String phone = request.getParameter("phone");
		String birthday = request.getParameter("birthday");
		String address = request.getParameter("address");
		//����У��:������ʽ
		String userpwdRegex = "[a-zA-Z0-9]{8,20}"; //8-20λ
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
				String updateSuccess = "�޸ĳɹ�!";
				/*
				String headimage = null;
				String imagePath = "D:/WYCjsp/headimage/" + username + ".jpg";
			    File file = new File(imagePath);
				if(!file.exists()) {
					headimage = "default.jpg";
					//System.out.println("������");
				}else{
					headimage = username + ".jpg";
					//System.out.println("����");
				}
				*/
				request.setAttribute("username", username);
				//request.setAttribute("headimage", headimage);//Я������
				request.setAttribute("updateSuccess", updateSuccess);//Я������
				//��ת���ɹ�ҳ��
				request.getRequestDispatcher("WEB-INF/jsp/AdminUpdateInformation.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			String updateError = "���ݲ�����Ҫ��!";
			/*
			String headimage = null;
			String imagePath = "D:/WYCjsp/headimage/" + username + ".jpg";
		    File file = new File(imagePath);
			if(!file.exists()) {
				headimage = "default.jpg";
				//System.out.println("������");
			}else{
				headimage = username + ".jpg";
				//System.out.println("����");
			}
			*/
			//request.setAttribute("headimage", headimage);//Я������
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
	
	//����Աע���û�
	@RequestMapping("/adminDeleteUser")
	public void adminDeleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//��ȡҳ������
		String username = request.getParameter("username");
		//System.out.println(username);
		//String current = request.getParameter("currentPage");
		//System.out.println("current:" + current);
		int allPage = 0;
		try {
			List<User> list = userService.AdminSelectAllUser();
			//������ҳ��
			int count = list.size();
			allPage = count / 5;
			if(count % 5 != 0){
				allPage = allPage + 1;
			}
			//����ҳ������ǰҳ������session�������
			HttpSession hs = request.getSession();
			hs.setAttribute("allPage", allPage);
			hs.setAttribute("currentPage", "1");
			//�޸�״̬
			userService.AdminDeleteUser(username);
			//��ȡ��ǰҳ5���û�����
			List<User> userList = userService.AdminSelectPageUser(0, 5);
			request.setAttribute("userList", userList);
			String success = "�û�ע���ɹ�!";
			request.setAttribute("success", success);
			request.getRequestDispatcher("WEB-INF/jsp/AdminManageUsers.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//����Ա�ָ��û�
	@RequestMapping("/adminRecoverUser")
	public void adminRecoverUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//��ȡҳ������
		String username = request.getParameter("username");
		//System.out.println(username);
		//String current = request.getParameter("currentPage");
		//System.out.println("current:" + current);
		int allPage = 0;
		try {
			List<User> list = userService.AdminSelectAllUser();
			//������ҳ��
			int count = list.size();
			allPage = count / 5;
			if(count % 5 != 0){
				allPage = allPage + 1;
			}
			//����ҳ������ǰҳ������session�������
			HttpSession hs = request.getSession();
			hs.setAttribute("allPage", allPage);
			hs.setAttribute("currentPage", "1");
			//�޸�״̬
			userService.AdminRecoverUser(username);
			//��ȡ��ǰҳ5���û�����
			List<User> userList = userService.AdminSelectPageUser(0, 5);
			request.setAttribute("userList", userList);
			String success = "�û��ָ��ɹ�!";
			request.setAttribute("success", success);
			request.getRequestDispatcher("WEB-INF/jsp/AdminManageUsers.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
