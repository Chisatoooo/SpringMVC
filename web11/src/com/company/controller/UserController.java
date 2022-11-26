package com.company.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.company.entity.User;
import com.company.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	//用户注册
	@RequestMapping("/userRegister")
	public void userRegister(User user, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String username = user.getUsername();
		String userpwd = user.getUserpwd();
		String phone = user.getPhone();
		String uname = user.getUname();
		String IDnumber = user.getIDnumber();
		String code = request.getParameter("code");
		//System.out.println("username: " + username + " userpwd: " + userpwd + " phone: " + phone + " code: " + code + " uname: " + uname + " IDnumber: " + IDnumber);
		//获取session的验证码
		HttpSession hs = request.getSession();
		StringBuffer phoneCode = (StringBuffer) hs.getAttribute("code");
		//System.out.println("phoneCode: " + phoneCode);
		//数据校验:正则表达式
		String usernameRegex = "[a-zA-Z][a-zA-Z0-9]{5,9}"; //首位为字母 6-10位
		String userpwdRegex = "[a-zA-Z0-9]{8,20}"; //8-20位
		String phoneRegex = "[1][3,4,5,7,8][0-9]{9}";
		String unameRegex = "^([\\u4e00-\\u9fa5]{2,6}|[a-zA-Z\\.\\s]{2,20})$"; //2-6位中文或2-20位英文
		String IDnumberRegex = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" + "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
		boolean usernameResult = username.matches(usernameRegex);
		System.out.println("username: " + usernameResult);
		boolean userpwdResult = userpwd.matches(userpwdRegex);
		System.out.println("userpwd: " + userpwdResult);
		boolean phoneResult = phone.matches(phoneRegex);
		System.out.println("phone: " + phoneResult);
		boolean unameResult = uname.matches(unameRegex);
		System.out.println("unameResult: " + unameResult);
		boolean IDnumberResult = IDnumber.matches(IDnumberRegex);
		System.out.println("IDnumber: " + IDnumberResult);	
		try {
			if(usernameResult == true && userpwdResult == true && phoneResult == true && unameResult == true && IDnumberResult == true){
				if(phoneCode.toString().equals(code)){
					userService.UserRegister(user);
					String registerResult = "1";
					//写到前端
			        PrintWriter pw = response.getWriter();
					pw.print(registerResult);
				}else{
					String registerResult = "5";
					//写到前端
			        PrintWriter pw = response.getWriter();
					pw.print(registerResult);
				}
			}else{
				String registerResult = "2";
				//写到前端
		        PrintWriter pw = response.getWriter();
				pw.print(registerResult);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			String registerResult = "3";
			//写到前端
	        PrintWriter pw = response.getWriter();
			pw.print(registerResult);
		}
	}
	
	//获取验证码
	@RequestMapping("/getPhoneCode")
	public void getPhoneCode(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String phone = request.getParameter("phone");
		//System.out.println("phone: " + phone);
		StringBuffer code = new StringBuffer();
		for(int i = 0; i < 4; i ++){
			int number = randomNumber(0,9);
			code.append(number);
		}
		System.out.println("code: " + code);
		//将验证码存到Session
		HttpSession hs = request.getSession();
		hs.setAttribute("code", code);
		/*
		HttpClient client = new HttpClient();
    	PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");    		                                                                                                                               post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
        NameValuePair[] data = { new NameValuePair("Uid", "wangyichao"),
                new NameValuePair("Key", "d41d8cd98f00b204e980"),
                new NameValuePair("smsMob", phone),
                new NameValuePair("smsText", "验证码：" + code) };//短信内容
        post.setRequestBody(data);
        client.executeMethod(post);
        post.releaseConnection();//释放连接
        */
	}
	
	//验证是否能按按钮
	@RequestMapping("/checkGetCode")
	public void checkGetCode(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String phone = request.getParameter("phone");
		//System.out.println("phone: " + phone);
		String phoneRegex = "[1][3,4,5,7,8][0-9]{9}";
		boolean phoneResult = phone.matches(phoneRegex);
		//System.out.println("phone: " + phoneResult);
		if(phoneResult == false){
			String getResult = "4";
			//写到前端
	        PrintWriter pw = response.getWriter();
			pw.print(getResult);
		}else{
			String getResult = "1";
			//写到前端
	        PrintWriter pw = response.getWriter();
			pw.print(getResult);
		}
	}
	
	//用户登录
	@RequestMapping("/userLogin")
	public void userLogin(User user, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String username = user.getUsername();
		//System.out.println("username: " + username);
		String userpwd = user.getUserpwd();
		//System.out.println("userpwd: " + userpwd);
		//将用户名存到Session
		HttpSession login = request.getSession();
		login.setAttribute("Login", username);
		try {
			if(username == ""){
				String result = "0";
				//写到前端
		        PrintWriter pw = response.getWriter();
		        pw.print(result);
			}else if(userpwd == ""){
				String result = "00";
				//写到前端
		        PrintWriter pw = response.getWriter();
		        pw.print(result);
			}else{
				String pwd = userService.selectPwd(username);
				//System.out.println("pwd: " + pwd);
				String state = userService.selectState(username);
				//System.out.println("state: " + state);
				if(pwd == null && userpwd != ""){
					String result = "3";
					//写到前端
			        PrintWriter pw = response.getWriter();
					pw.print(result);
				}else if(userpwd.equals(pwd)){
					if(state.equals("已注册")){
						String result = "1";
						String headimage = null;
						String path = "D:/WYCjsp/headimage/" + username + ".jpg";
					    File file = new File(path);
						if(!file.exists()) {
							headimage = "default.jpg";
							//System.out.println("不存在");
						}else{
							headimage = username + ".jpg";
							//System.out.println("存在");
						}
						//写到前端
				        PrintWriter pw = response.getWriter();
						pw.print(result);
						//将用户名和头像存到Session
						HttpSession hs = request.getSession();
						hs.setAttribute("username", username);
						hs.setAttribute("headimage", headimage);
					}else{
						String result = "4";
						//写到前端
				        PrintWriter pw = response.getWriter();
						pw.print(result);
					}
				}else if(!userpwd.equals(pwd) && userpwd != ""){
					String result = "2";
					//写到前端
			        PrintWriter pw = response.getWriter();
					pw.print(result);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//用户个人信息
	@RequestMapping("/userSelectInformation")
	public void userSelectInformation(User user, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取session的数据
		HttpSession hs = request.getSession();
		String username = (String) hs.getAttribute("username");
		String headimage = (String) hs.getAttribute("headimage");
		//System.out.println("headimage: " + headimage);
		//System.out.println("username: " + username);
		//ModelAndView modelAndView = new ModelAndView();
		try {
			List<User> userList = userService.queryOneUser(username);
			//modelAndView.addObject("userList", userList);
			//modelAndView.setViewName("UserInformation.jsp");
			request.setAttribute("userList", userList);
			request.setAttribute("headimage", headimage);
			request.getRequestDispatcher("WEB-INF/jsp/UserInformation.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return modelAndView;
	}
	
	//用户修改个人信息
	@RequestMapping("/userUpdateInformation")
	public void userUpdateInformation(User user, HttpServletRequest request, HttpServletResponse response) throws IOException{
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
				request.setAttribute("headimage", headimage);//携带数据
				request.setAttribute("updateSuccess", updateSuccess);//携带数据
				//跳转到成功页面
				request.getRequestDispatcher("WEB-INF/jsp/UserUpdateInformation.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			String updateError = "数据不符合要求!";
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
			try {
				request.setAttribute("updateError", updateError);
				request.setAttribute("headimage", headimage);//携带数据
				request.getRequestDispatcher("WEB-INF/jsp/UserUpdateInformation.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//用户上传头像
	@RequestMapping("/userUpdateHeadimage")
	public void userUpdateHeadimage(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String path = "D:\\WYCjsp\\headimage\\";
		//获取session的数据
		HttpSession hs = request.getSession();
		String username = (String) hs.getAttribute("username");
		//获取源文件名称
		String fileName = file.getOriginalFilename();
		String[] str = fileName.split("\\.");
		String newFile = path + username + "." + str[1];
		File fPath = new File(newFile);
		String headimage = username + "." + str[1];
		try {
			file.transferTo(fPath);
			userService.UserUpdateHeadimage(headimage, username);
			String headSuccess = "修改成功!";
			request.setAttribute("headimage", headimage);
			request.setAttribute("headSuccess", headSuccess);
			request.getRequestDispatcher("WEB-INF/jsp/UserUpdateInformation.jsp").forward(request, response);
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
	
	//跳转用户登陆成功
	@RequestMapping("/userLoginSkip")
	public void userLoginSkip(User user, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		try {
			//获取页面数据
			String username = request.getParameter("username");
			request.setAttribute("username", username);
			//System.out.println("username: " + username);
			request.getRequestDispatcher("WEB-INF/jsp/UserLoginSuccess.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//用户跳转查看信息
	@RequestMapping("/userSkipSelectInformation")
	public void userSkipSelectInformation(User user, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取session
		HttpSession hs = request.getSession();
		String username = (String) hs.getAttribute("username");
		//System.out.println("username: " + username);
		String headimage = null;
		String path = "D:/WYCjsp/headimage/" + username + ".jpg";
	    File file = new File(path);
		if(!file.exists()) {
			headimage = "default.jpg";
			//System.out.println("不存在");
		}else{
			headimage = username + ".jpg";
			//System.out.println("存在");
		}
		request.setAttribute("headimage", headimage);
		//System.out.println("informationheadimage: " + headimage);
		try {
			List<User> userList = userService.queryOneUser(username);
			request.setAttribute("userList", userList);
			request.getRequestDispatcher("WEB-INF/jsp/UserInformation.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//用户跳转修改信息
	@RequestMapping("/userSkipUpdateInformation")
	public void userSkipUpdateInformation(User user, HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取session
		HttpSession hs = request.getSession();
		String username = (String) hs.getAttribute("username");
		System.out.println("username: " + username);
		String headimage = null;
		String path = "D:/WYCjsp/headimage/" + username + ".jpg";
	    File file = new File(path);
		if(!file.exists()) {
			headimage = "default.jpg";
			//System.out.println("不存在");
		}else{
			headimage = username + ".jpg";
			//System.out.println("存在");
		}
		try {
			request.setAttribute("headimage", headimage);
			request.setAttribute("username", username);
			request.getRequestDispatcher("WEB-INF/jsp/UserUpdateInformation.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static int randomNumber(int min, int max) { //产生随机数范围
		double number = Math.random();
		int result = min + (int)(number * (max - min));
		return result;
	}
}
