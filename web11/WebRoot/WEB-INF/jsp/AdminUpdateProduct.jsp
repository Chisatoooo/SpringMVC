<%@ page language="java" import="java.util.*, com.company.entity.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理员修改商品</title>
    
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
			width: 230px;
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
			top: 250px;
			left: 0px;
		}
		#label1{
			color: red;
			width: 200px;
			height: 30px;
			position: absolute;
			top: 310px;
			left: 5px;
			font-size: 20px;
		}
		#label2{
			color: red;
			width: 200px;
			height: 30px;
			position: absolute;
			top: 310px;
			left: 5px;
			font-size: 20px;
		}
  </style>
  </head>
  <script>
  	function checkData(){
  		var price = document.forms["form1"]["price"].value;
  		if(price == null || price == ""){
  			alert("请输入商品价格!");
  			return false;
  		}
 		return true;
  	}
  </script>
  <% 
  	 String id = (String) request.getAttribute("id");
  	 
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
  	<h1>管理员修改商品</h1>
  	<form id="form1" action="adminUpdateProduct.action" method="post" onsubmit="return checkData()">	
  		该商品编号:<input class="box" type="text" name="id" autocomplete="off" readonly="readonly" value="<%=id %>"/><br><br>
   		新的商品价格:<input class="box" type="text" name="price" oninput="value=value.replace(/[^\d]/g,'')" autocomplete="off" placeholder="请输入新的价格（1-4位数字）"/><br><br>
    	<input id="b1" class="button" type="submit" value="修改商品"/>
    	<label id="label1"><%=updateSuccess %></label>
    	<label id="label2"><%=updateError %></label>
    </form>
  </body>
</html>
