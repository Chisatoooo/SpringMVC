<%@ page language="java" import="java.util.*, com.company.entity.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户订单</title>
    
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
	    	width: 120px;
	    	height: 50px;
	    }
	    tr{
	    	width: 120px;
	    	height: 50px;
	    }
	    td{
	    	width: 120px;
	    	height: 80px;
	    }
	    #tdname{
	    	width: 150px;
	    	font-size: 20px;
	    	background: linear-gradient(to right, blue, purple, blue);
          	-webkit-background-clip: text;
          	color: transparent;
	    }
	    #tdpaystate{
	    	font-size: 25px;
	    	color: red;
	    }
	    #tddeliverystate{
	    	font-size: 25px;
	    	color: red;
	    }
	    #tdorderstate{
	    	font-size: 25px;
	    	color: red;
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
	    #label1{
	    	position: absolute;
	    	color: red;
	    	font-size: 25px;
	    }
	</style>
  </head>
  <% 
  	 List<Orders> ordersList = (List<Orders>) request.getAttribute("ordersList");
     String username = (String) request.getAttribute("username");
     if(username == null){
     	username = "请先登录!";	
     }
     String success = (String) request.getAttribute("success");
     //System.out.println("success: " + success);
     if(success == null){
     	success = "";
     }
  %>
  <body>
    <span id="span1">您的订单表</span>
    <span id="span2">欢迎您 : <%=username %> !</span>
    <form action="userSkipShoppingCar.action" method="post">
  		<input id="car" class="button" type="submit" value="">
  	</form>
  	<label id="label1"><%=success %></label>
    <table id="table1" border="1" cellspacing= "0">
       <tr>
          <th>用户名称</th>
          <th>用户手机号</th>
          <th>用户地址</th>
          <th>商品编号</th>
          <th>支付状态</th>
          <th>付款日期</th>
          <th>发货状态</th>
          <th>订单状态</th>
       </tr>
       <% 	
       		for(int i = 0; i < ordersList.size(); i ++){ 
       %>
	   <tr>
	      	<td id="tdname"><%= ordersList.get(i).getFk_order_username() %></td>
	      	<td id="tdphone"><%= ordersList.get(i).getFk_phone() %></td>
	      	<td id="tdaddress"><%= ordersList.get(i).getFk_address() %></td>
	      	<td id="tdid"><%= ordersList.get(i).getFk_product_id() %></td>
	      	<td id="tdpaystate"><%= ordersList.get(i).getPaystate() %></td>
	      	<td id="tddate"><%= ordersList.get(i).getDate() %></td>
	      	<td id="tddeliverystate"><%= ordersList.get(i).getDeliverystate() %></td>
	      	<td id="tdorderstate"><%= ordersList.get(i).getOrderstate() %></td>
	   </tr>
	   <% } %>
    </table>
  </body>
</html>
