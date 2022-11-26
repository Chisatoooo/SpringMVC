<%@ page language="java" import="java.util.*, com.company.entity.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>商品</title>
    
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
    		font-size: 18px;
        	color: #ccc;
      	}
    	body{
    		background: #deeefb;
    		height: 1050px;
    	}
    	#span1{
    		position: absolute;
       		left: 520px;
          	top: 30px;
          	font-size: 30px;
          	background: linear-gradient(to right, red, green, blue);
          	-webkit-background-clip: text;
          	color: transparent;
    	}
    	#span2{
    		top: 13px;
    		right: 20px;
    		color: red;
    		position: fixed;
    	}
		table{
	        border: 1px solid black;
	        position: absolute;
	        left: 280px;
	        top: 200px;
	        text-align: center;
	    }
	    th{
	    	width: 130px;
	    	height: 60px;
	    }
	    #thname{
	    	width: 180px;
	    }
	    #thimg{
	    	width: 220px;
	    }
	    tr{
	    	width: 130px;
	    	height: 60px;
	    }
	    td{
	    	width: 130px;
	    	height: 100px;
	    }
	    #tdid{
	    	font-size: 30px;
	    	color: black;
	    }
	    #tdname{
	    	width: 160px;
	    	font-size: 20px;
	    	background: linear-gradient(to right, blue, purple, blue);
          	-webkit-background-clip: text;
          	color: transparent;
          	width: 180px;
	    }
	    #tdprice{
	    	font-size: 30px;
	    	color: red;
	    }
	    #tdtype{
	    	font-size: 20px;
	    	background: linear-gradient(to right, blue, purple, blue);
          	-webkit-background-clip: text;
          	color: transparent;
	    }
	    img{
	    	width: 200px;
	    	height: 200px;
	    	padding: 10px;
	    }
	    #next{
	        position: absolute;
	        left: 790px;
	        top: 1000px;
	    }
	    #previous{
	        position: absolute;
	        left: 640px;
	        top: 1000px;
	    }
	    #number{
	        position: absolute;
	        left: 728px;
	        top: 1000px;
	        font-size: 20px;
	        text-align: center;
	    }
	    .button{
	    	width: 100px;
	    	height: 60px;
	    	background: white;
	    }
	    input:hover{
	    	cursor: pointer;
	    	color: red;
	    	border: 2px solid blue;
	    }
	    .page{
	    	width: 60px;
	    	height: 30px;
	    	background: white;
	    }
	    #querytext{
	    	width: 450px;
	    	height: 50px;
	    	position: absolute;
	        left: 500px;
	        top: 100px;
	        border: 2px solid #ff7a3d;
	        border-radius: 2%;
	        font-size: 20px;
	    }
	    #querytext:hover{
	    	cursor: text;
	    	color: black;
	    }
	    #querybutton{
	    	position: absolute;
	    	width: 90px;
	    	height: 50px;
	        left: 940px;
	        top: 100px;
	        border: 2px solid #ff7a3d;
	        border-radius: 0% 45% 45% 0%;
	        font-size: 20px;
	    }
	    #querybutton:hover{
	    	color: blue;
	    }
	    #label1{
	    	position: absolute;
	    	color: red;
	    	font-size: 25px;
	    }
	    #car{
	    	background: url("image/car.png");
  			background-size: cover;
  			width: 30px;
	    	height: 30px;
  			position: fixed;
    		right: 170px;

    		border: none;
	    }
	    #car:hover{
	    	transform: scale(1.2);
	    }
    </style>
  </head>
  <% 
  	 List<Product> productList = (List<Product>) request.getAttribute("productList");
     String picture = (String) request.getAttribute("picture");
     String username = (String) application.getAttribute("username");
     if(username == null){
     	username = "请先登录";	
     }
     String currentPage = (String) request.getAttribute("currentPage");
     String nextPage = (String) request.getAttribute("nextPage");
     String previousPage = (String) request.getAttribute("previousPage");
     //System.out.println("currentPage: " + currentPage);
     if(currentPage == null){
     	currentPage = "1";	
     }
     //System.out.println("nextpage: " + nextPage);
     if(nextPage == null){
		nextPage = "下一页";
		previousPage = "上一页";
     }
     //System.out.println("previousPage: " + previousPage);
     if(previousPage == null){
		previousPage = "上一页";
	 }
	 if(currentPage == "1"){
     	previousPage = "首页";
     }
     
     String success = (String) request.getAttribute("success");
     //System.out.println("success: " + success);
     if(success == null){
     	success = "";
     }
  %>
  <script>
  	function checkData(){
  		var producttext = document.forms["form1"]["producttext"].value;
  		if(producttext == null || producttext == ""){
  			alert("请正确输入搜索信息!");
  			return false;
  		}
  		return true;
  	}
  	
  	window.onload = function(){
  		var oNextpage = document.getElementById("nextpage");
  		var oPreviouspage = document.getElementById("previouspage");
  		if(oPreviouspage.value == "首页"){
  			oPreviouspage.setAttribute("disabled", false);
  			oPreviouspage.style.cursor = "not-allowed";
  			oPreviouspage.style.color = "#c2c2c2";
  			oPreviouspage.style.border = "none";
  		}
  		if(oNextpage.value == "尾页"){
  			oNextpage.setAttribute("disabled", false);
  			oNextpage.style.cursor = "not-allowed";
  			oNextpage.style.color = "#c2c2c2";
  			oNextpage.style.border = "none";
  		}
  	}
  </script>
  
  <body>
  	<span id="span1">欢迎来到本小店&nbsp;&nbsp;&nbsp;以下为本店的商品</span>
  	<span id="span2">欢迎您 : <%=username %> !</span>
  	<label id="label1"><%=success %></label>
  	<form id="form1" action="userQueryProduct.action" method="post" onsubmit="return checkData()">
  		<input id="querytext" type="text" name="producttext" autocomplete="off" placeholder=" 请输入您喜欢的商品信息 （以价格降序排列）"/>
  		<input id="querybutton" class="button" type="submit" value="搜 索">
  	</form>
  	<form action="userSkipShoppingCar.action" method="post">
  		<input id="car" class="button" type="submit" value="">
  	</form>
    <table id="table1" border="1" cellspacing= "0">
       <tr>
          <th>商品编号</th>
          <th id="thname">商品名称</th>
          <th id="thimg">商品图片</th>
          <th>商品价格</th>
          <th>商品类型</th>
          <th>选项</th>
       </tr>
       <% for(int i = 0; i < productList.size(); i ++){ %>
	      <tr>
	      <form action="addShoppingCar.action" method="post">
	      	<td id="tdid"><%= productList.get(i).getId() %></td>
	      	<input type="hidden" name="id" value="<%= productList.get(i).getId() %>">
	      	<td id="tdname"><%= productList.get(i).getName() %></td>
	      	<input type="hidden" name="name" value="<%= productList.get(i).getName() %>">
	      	<td><img src=<%="/picture/" + productList.get(i).getImage() %>></td>
	      	<td id="tdprice">￥ <%= productList.get(i).getPrice() %></td>
	      	<input type="hidden" name="price" value="<%= productList.get(i).getPrice() %>">
	      	<td id="tdtype"><%= productList.get(i).getType() %></td>
	      	<input type="hidden" name="username" value="<%=username %>">
	      	<td><input style="font-size: 16px;" class="button" type="submit" value="加入购物车"></td>
	      </form>
	      </tr>
       <% } %>
    </table>
    
   <form id="next" action="userNextProductPage.action" method="post">
       <input id="nextpage" class="page" type="submit" value=<%=nextPage %>>
   </form>
   <label id="number"><%=currentPage %>/${allPage}</label>
   <form id="previous" action="userPreviousProductPage.action" method="post">
       <input id="previouspage" class="page" type="submit" value=<%=previousPage %>>
   </form>
    
  </body>
</html>
