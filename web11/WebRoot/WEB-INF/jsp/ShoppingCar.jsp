<%@ page language="java" import="java.util.*, com.company.entity.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>购物车</title>
    
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
    		background: #deeefb;
    	}
		table{
	        border: 1px solid black;
	        position: absolute;
	        left: 220px;
	        top: 120px;
	        text-align: center;
	    }
	    th{
	    	width: 130px;
	    	height: 60px;
	    }
	    #thname{
	    	width: 150px;
	    }
	    tr{
	    	width: 130px;
	    	height: 60px;
	    }
	    td{
	    	width: 130px;
	    	height: 100px;
	    }
	    #in1{
	    	width: 120px;
	    	border: none;
	    	text-align: center;
	    	background: #deeefb;
	    	font-size: 20px;
	    }
	    #in1:hover{
	    	cursor: text;
	    	color: black;
	    }
	    #tdname{
	    	width: 150px;
	    	font-size: 20px;
	    	background: linear-gradient(to right, blue, purple, blue);
          	-webkit-background-clip: text;
          	color: transparent;
	    }
	    #tdid{
	    	font-size: 25px;
	    	color: black;
	    }
	    #tdnumber{
	    	font-size: 25px;
	    	color: black;
	    }
	    #tdprice{
	    	font-size: 25px;
	    	color: red;
	    }
	    #tdsumprice{
	    	font-size: 25px;
	    	color: red;
	    }
	    img{
	    	width: 200px;
	    	height: 200px;
	    	padding: 10px;
	    }
	    #span1{
    		position: absolute;
       		left: 650px;
          	top: 30px;
          	font-size: 40px;
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
    	#label1{
    		position: absolute;
	    	color: red;
	    	font-size: 25px;
	    }
	    .button{
	    	width: 90px;
	    	height: 50px;
	    	background: white;
	    }
	    input:hover{
	    	cursor: pointer;
	    	color: red;
	    	border: 2px solid blue;
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
	    #allbuy{
	    	width: 100px;
	    	height: 60px;
	    	font-size: 20px;
	    	border: 2px solid black;
	    	border-radius: 5%;
	    	position: absolute;
	    	left: 1160px;
	    	top: 40px;
	    	height: 50px;
	    }
	    #allprice{
	    	font-size: 22px;
	    	border: 2px solid black;
	    	position: absolute;
	    	left: 1000px;
	    	top: 50px;
	    	width: 140px;
	    	height: 30px;
	    }
	    #allprice:hover{
	    	cursor: text;
	    }
	</style>
  </head>
  <script type="text/javascript">
  	window.onload = function(){
  		var oAllbuy = document.getElementById("allbuy");
  		var oAllprice = document.getElementById("allprice");
  		if(oAllprice.innerHTML == "总价 : 0"){
  			oAllbuy.setAttribute("disabled", false);
  			oAllbuy.style.cursor = "not-allowed";
  			oAllbuy.style.color = "#c2c2c2";
  			oAllbuy.style.border = "none";
  		}
  		
  		var oResult = document.getElementById("result");
  		if(oResult.value != ""){
  			alert("地址未填写, 请先完善信息!");
  		}
  	}
  </script>
  <% 
     List<ShoppingCar> shoppingCarList = (List<ShoppingCar>) request.getAttribute("shoppingCarList");
     String username = (String) request.getAttribute("username");
     if(username == null){
     	username = "请先登录";	
     }
     String success = (String) request.getAttribute("success");
     if(success == null){
     	success = "";
     }
     String result = (String) request.getAttribute("result");
     if(result == null){
     	result = "";
     }
  %>
  <body>
    <span id="span1">您的购物车</span>
    <span id="span2">欢迎您 : <%=username %> !</span>
    <label id="label1"><%=success %></label>
    <form action="userSkipShoppingCar.action" method="post">
  		<input id="car" class="button" type="submit" value="">
  	</form>
    <table id="table1" border="1" cellspacing="0">
       <tr>
          <th>用户名称</th>
          <th id="thname">商品名称</th>
          <th>商品编号</th>
          <th>商品数量</th>
          <th>商品单价</th>
          <th>商品总价</th>
          <th>购买选项</th>
          <th>删除选项</th>
       </tr>
       <% 	
       		int allprice = 0;
       		for(int i = 0; i < shoppingCarList.size(); i ++){ 
				allprice = allprice + shoppingCarList.get(i).getSumprice();
       %>
	   <tr>
	      <form action="buyProduct.action" method="post">
	      	<td><input id="in1" type="text" name="username" value="<%= shoppingCarList.get(i).getFk_username() %>"></td>
	      	<td id="tdname"><%= shoppingCarList.get(i).getProductname() %></td>
	      	<input type="hidden" name="productname" value="<%= shoppingCarList.get(i).getProductname() %>">
	      	<td id="tdid"><%= shoppingCarList.get(i).getFk_id() %></td>
	      	<input type="hidden" name="fkId" value="<%= shoppingCarList.get(i).getFk_id() %>">
	      	<td id="tdnumber"><%= shoppingCarList.get(i).getNumber() %> 个</td>
	      	<td id="tdprice">￥ <%= shoppingCarList.get(i).getPrice() %></td>
	      	<td id="tdsumprice">￥ <%= shoppingCarList.get(i).getSumprice() %></td>
	      	<input type="hidden" name="sumprice" value="<%= shoppingCarList.get(i).getSumprice() %>">
	      	<td><input style="font-size: 20px;" class="button" type="submit" value="购 买"></td>
	      </form>
	      <form action="deleteShoppingCar.action" method="post">
	      	<input type="hidden" name="fkId" value="<%= shoppingCarList.get(i).getFk_id() %>">
	      	<input type="hidden" name="username" value="<%= shoppingCarList.get(i).getFk_username() %>">
	      	<td><input style="font-size: 20px;" class="button" type="submit" value="删 除"></td>
	      </form>
	   </tr>
	   <% } %>
    </table>
    <span id="allprice">总价 : <%=allprice %></span>
    <form action="buyAllProduct.action" method="post">
    	<input name="sumprice" type="hidden" value="<%=allprice %>">
    	<input name="productname" type="hidden" value="清空购物车">
		<input id="allbuy" class="button" type="submit" value="一键购买">
	</form>
	<input id="result" type="hidden" value="<%=result %>">
  </body>
</html>
