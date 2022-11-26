<%@ page language="java" import="java.util.*, com.company.entity.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理员添加商品</title>
    
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
			top: 400px;
			left: 0px;
		}
		#label1{
			color: red;
			width: 200px;
			height: 30px;
			position: absolute;
			top: 460px;
			left: 0px;
			font-size: 20px;
		}
		#label2{
			color: red;
			width: 200px;
			height: 30px;
			position: absolute;
			top: 460px;
			left: 0px;
			font-size: 20px;
		}
		#file{

			top: 280px;
			left: 0px;
		}
		#file:hover{
			cursor: pointer;
			color: red;
		}
  </style>
  </head>
  <script>
  	function checkData(){
  		var name = document.forms["form1"]["name"].value;
  		var price = document.forms["form1"]["price"].value;
  		var file = document.forms["form1"]["file"].value;
  		var type = document.forms["form1"]["type"].value;
  		if(name == null || name == ""){
  			alert("请输入商品名称!");
  			return false;
  		}else if(price == null || price == ""){
  			alert("请输入商品价格!");
  			return false;
  		}else if(file == null || file == ""){
  			alert("请选择商品图片!");
  			return false;
  		}else if(type == null || type == ""){
  			alert("请输入商品类型!");
  			return false;
  		}
 		return true;
  	}
  </script>
  <% 
 	 String addSuccess = (String) request.getAttribute("addSuccess");
 	 if(addSuccess == null){
 	 	addSuccess = "";
 	 }	 
 	 String addError = (String) request.getAttribute("addError");
 	 if(addError == null){
 	 	addError = "";
 	 }
 %>
  <body>
  	<h1>管理员添加商品</h1>
  	<form id="form1" action="adminAddProduct.action" method="post" onsubmit="return checkData()" enctype="multipart/form-data">	
  		商品名称:<input class="box" type="text" name="name" autocomplete="off" placeholder="请输入商品名称"><br><br>
   		商品价格:<input class="box" type="text" name="price" oninput="value=value.replace(/[^\d]/g,'')" autocomplete="off" placeholder="请输入商品价格"><br><br>
   		商品图片:<br><input id="file" type="file" name="file"><br><br>
   		商品类型:<input class="box" type="text" name="type" autocomplete="off" placeholder="请输入商品类型"><br><br>
    	<input id="b1" class="button" type="submit" value="添加商品">
    	<label id="label1"><%=addSuccess %></label>
    	<label id="label2"><%=addError %></label>
    </form>
  </body>
</html>
