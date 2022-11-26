<%@ page language="java" import="java.util.*, com.company.entity.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户前台功能</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <style>
    	input::-webkit-input-placeholder {
  			color: #ccc;
		}
  		body{
			width: 200px;
			height: 800px;
			position: absolute;
			top: 50px;
			left: 150px;
			background: url("image/1.png");
			background-size: cover;
		}
		.box{
			width: 200px;
			height: 40px;
		}
		.button{
			width: 150px;
			height: 40px;
			background: white;
			color: #665758;
			font-size: 20px;
			border: 1px solid #ccc;
		}
		.button:hover{
			cursor: pointer;
			opacity: 60%;
		}
		#b1{
			width: 200px;
			height: 40px;
			position: absolute;
			top: 720px;
			left: 0px;
			background: white;
			color: #665758;
			font-size: 20px;
			border: 1px solid #ccc;
		}
		#b2{
			width: 200px;
			height: 40px;
			position: absolute;
			top: 720px;
			left: 150px;
			background: white;
			color: #665758;
			font-size: 20px;
			border: 1px solid #ccc;
		}
		#img{
			width: 200px;
			height: 200px;
			position: absolute;
			top: 85px;
			left: 260px;
			border: 1px solid black;
			border-radius: 50%;
		}
  </style>
  </head>

  <% 
 	 String username = (String) application.getAttribute("username");
 	 String headimage = (String) request.getAttribute("headimage");
 	 List<User> userList = (List<User>) request.getAttribute("userList");
  %>
  <body>
  	<h1>用户个人信息</h1>
  	<form action="userSkipUpdateInformation.action" method="post">	
  		您的账号:<input class="box" type="text" name="username" readonly="readonly" value="<%=username %>"/><br><br>
   		您的密码:<input class="box" type="text" name="userpwd" readonly="readonly" value="<%=userList.get(0).getUserpwd() %>"/><br><br>
   		您的姓名:<input class="box" type="text" name="uname" readonly="readonly" value="<%=userList.get(0).getUname() %>"/><br><br>
   		您的身份证:<input class="box" type="text" name="IDnumber" readonly="readonly" value="<%=userList.get(0).getIDnumber() %>"/><br><br>
   		您的联系方式:<input class="box" type="text" name="phone" readonly="readonly" value="<%=userList.get(0).getPhone() %>"/><br><br>
   		您的生日:<input class="box" type="text" name="birthday" readonly="readonly" value="<%=userList.get(0).getBirthday() %>"/><br><br>
   		您的地址:<input class="box" type="text" name="address" readonly="readonly" value="<%=userList.get(0).getAddress() %>"/><br><br>
   		您的注册日期:<input class="box" type="text" name="date" readonly="readonly" value="<%=userList.get(0).getDate() %>"/><br><br>
    	<input id="b1" class="button" type="submit" value="前往修改资料"/>
    </form>
    
    <img id="img" src=<%="/headimage/" + headimage %>> 
  </body>
</html>
