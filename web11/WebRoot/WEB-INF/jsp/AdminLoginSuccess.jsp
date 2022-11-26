<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理员后台功能</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <style>
  	body{
  		background: url("image/1.png");
  		background-size: cover;
  	}
	#form1{
		position: absolute;
		top: 140px;
		left: 60px;
	}
	#form2{
		position: absolute;
		top: 220px;
		left: 60px;
	}
	#form3{
		position: absolute;
		top: 300px;
		left: 60px;
	}
	#form4{
		position: absolute;
		top: 380px;
		left: 60px;
	}
	#span{
		position: absolute;
		top: 50px;
		left: 10px;
		font-size: 30px;
		color: red;
	}
	.input{
		width: 160px;
		height: 40px;
		background: white;
		color: #665758;
		font-size: 20px;
		border: 1px solid #ccc;
	}
	.input:hover{
		cursor: pointer;
		opacity: 60%;
	}
  </style>
  </head>
  <% 
  	 String adminname = (String) request.getAttribute("adminname");
     application.setAttribute("adminname", adminname);
  %>
  <body>
  	<span id="span">欢迎你 : 管理员 <%=adminname %> !</span>
    <form id="form1" action="adminQueryAllUser.action" method="post" target="_blank">
    	<input class="input" type="submit" value="用户管理">
    </form>
    <form id="form2" action="adminSkipProduct.action" method="post" target="_blank">
    	<input class="input" type="submit" value="商品管理">
    </form>
    <form id="form3" action="adminSkipOrders.action" method="post" target="_blank">
    	<input class="input" type="submit" value="订单管理">
    </form>
    <form id="form4" action="AdminExit.jsp" method="post" target="_blank">
    	<input class="input" type="submit" value="退&nbsp;&nbsp;&nbsp;出">
    </form>
  </body>
</html>
