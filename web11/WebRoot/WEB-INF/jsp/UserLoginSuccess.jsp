<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>个人信息</title>
    
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
	#form5{
		position: absolute;
		top: 460px;
		left: 60px;
	}
	#form6{
		position: absolute;
		top: 550px;
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
  <% //String headimage = (String) request.getAttribute("headimage");
     String username = (String) request.getParameter("username");
     application.setAttribute("username", username);
  %>
  <body>
  	<span id="span">欢迎你 : <%=username %> !</span>
    <form id="form1" action="userSkipSelectInformation.action" method="post" target="_blank">
    	<input class="input" type="submit" value="查看个人信息">
    </form>
    <form id="form2" action="userSkipProduct.action" method="post" target="_blank">
    	<input class="input" type="submit" value="进入商品页面">
    </form>
    <form id="form3" action="userSkipUpdateInformation.action" method="post" target="_blank">
    	<input class="input" type="submit" value="完善个人信息">
    </form>
    <form id="form4" action="userSkipShoppingCar.action" method="post" target="_blank">
    	<input class="input" type="submit" value="查看购物车">
    </form>
    <form id="form5" action="userSkipOrders.action" method="post" target="_blank">
    	<input class="input" type="submit" value="查看个人订单">
    </form>
    <form id="form6" action="UserExit.jsp" method="post" target="_blank">
    	<input class="input" type="submit" value="退&nbsp;&nbsp;&nbsp;出">
    </form>
  </body>
</html>
