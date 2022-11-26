<%@ page language="java" import="java.util.*, com.company.entity.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理员修改信息</title>
    
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
			height: 150px;
			position: absolute;
			top: 50px;
			left: 150px;
			background: url("image/1.png");
			background-size: cover;
		}
		h1{
			width: 300px;
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
			top: 480px;
			left: 0px;
		}
		#b2{
			width: 160px;
			height: 40px;
			position: absolute;
			top: 320px;
			left: 280px;
		}
		#label3{
			width: 200px;
			height: 40px;
			position: absolute;
			top: 525px;
			left: 0px;
			color: red;
			text-align: center;
		}
		#label4{
			width: 200px;
			height: 40px;
			position: absolute;
			top: 525px;
			left: 0px;
			color: red;
			text-align: center;
		}
		#img{
			width: 200px;
			height: 200px;
			position: absolute;
			top: 30px;
			left: 260px;
			border: 1px solid black;
			border-radius: 50%;
		}
		#file{
			position: absolute;
			top: 250px;
			left: 280px;
		}
		#file:hover{
			cursor: pointer;
			color: red;
		}
		#label1{
			color: red;
			width: 80px;
			height: 30px;
			position: absolute;
			top: 290px;
			left: 320px;
		}
		#label2{
			color: red;
			width: 200px;
			height: 30px;
			position: absolute;
			top: 290px;
			left: 320px;
		}
		#in2{
	    	visibility: hidden;
	    }
  </style>
  </head>
  <script>
  	function checkData(){
  		var userpwd = document.forms["form1"]["userpwd"].value;
  		var address = document.forms["form1"]["address"].value;
  		var birthday = document.forms["form1"]["birthday"].value;
  		var phone = document.forms["form1"]["phone"].value;
  		if(userpwd == null || userpwd == ""){
  			alert("请正确输入密码!");
  			return false;
  		}else if(phone == null || phone == ""){
  			alert("请正确输入手机号!");
  			return false;
  		}else if(birthday == null || birthday == ""){
  			alert("请正确输入生日!");
  			return false;
  		}else if(address == null || address == ""){
  			alert("请正确输入地址!");
  			return false;
  		}
 		return true;
  	}
  </script>
  <% 
     String username = (String) request.getAttribute("username");
 	 String updateSuccess = (String) request.getAttribute("updateSuccess");
 	 if(updateSuccess == null){
 	 	updateSuccess = "";
 	 }	 
 	 String updateError = (String) request.getAttribute("updateError");
 	 if(updateError == null){
 	 	updateError = "";
 	 }
 %>
  <body>
  	<h1>管理员修改信息</h1>
  	<form id="form1" action="adminUpdateUser.action" method="post" onsubmit="return checkData()">	
  		该用户账号:<input class="box" type="text" name="username" readonly="readonly" value="<%=username %>"/><br><br>
   		修改密码:<input class="box" type="password" name="userpwd" autocomplete="off" placeholder="请输入新的密码"/><br><br>
   		修改联系方式:<input class="box" type="text" name="phone" autocomplete="off" placeholder="请输入新的手机号"/><br><br>
   		修改生日:<input class="box" type="text" name="birthday" autocomplete="off" placeholder="请输入新的生日"/><br><br>
   		修改地址:<input class="box" type="text" name="address" autocomplete="off" placeholder="请输入您的地址"/><br><br>
    	<input id="b1" class="button" type="submit" value="修改用户信息"/>
    	<label id="label3"><%=updateSuccess %></label>
    	<label id="label4"><%=updateError %></label>
    </form>

  </body>
</html>
