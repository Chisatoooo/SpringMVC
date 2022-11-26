<%@ page language="java" import="java.util.*, com.company.entity.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理订单</title>
    
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
       		left: 650px;
          	top: 30px;
          	font-size: 30px;
          	background: linear-gradient(to right, red, green, blue);
          	-webkit-background-clip: text;
          	color: transparent;
    	}
		table{
	        border: 1px solid black;
	        position: absolute;
	        left: 65px;
	        top: 100px;
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
	    .button{
	    	width: 80px;
	    	height: 50px;
	    	background: white;
	    }
	    .button:hover{
	    	border: 2px solid blue;
	    	cursor: pointer;
	    	color: red;
	    }
	    #label1{
	    	color: red;
	    	font-size: 25px;
	    }
    </style>
  </head>
  <% 
  	 List<Orders> ordersList = (List<Orders>) request.getAttribute("ordersList");
  	 String success = (String) request.getAttribute("success");
     //System.out.println("success: " + success);
     if(success == null){
     	success = "";
     }
  %>
  
  <body>
  	<span id="span1">以下为全部订单</span>
  	<label id="label1"><%=success %></label>
    <table id="table1" border="1" cellspacing= "0">
       <tr>
          <th>订单编号</th>
          <th>用户名称</th>
          <th>用户手机号</th>
          <th>用户地址</th>
          <th>商品编号</th>
          <th>支付状态</th>
          <th>付款日期</th>
          <th>发货状态</th>
          <th>订单状态</th>
          <th>修改发货状态</th>
          <th>修改订单状态</th>
       </tr>
       <% for(int i = 0; i < ordersList.size(); i ++){ %>
	      <tr>
	      <form action="adminUpdateOrders.action" method="post">
	      	<td id="tdoid"><%= ordersList.get(i).getOid() %></td>
	      	<input type="hidden" name="oid" value="<%= ordersList.get(i).getOid() %>">
	      	<td id="tdname"><%= ordersList.get(i).getFk_order_username() %></td>
	      	<td id="tdphone"><%= ordersList.get(i).getFk_phone() %></td>
	      	<td id="tdaddress"><%= ordersList.get(i).getFk_address() %></td>
	      	<td id="tdpid"><%= ordersList.get(i).getFk_product_id() %></td>
	      	<td id="tdpaystate"><%= ordersList.get(i).getPaystate() %></td>
	      	<td id="tddate"><%= ordersList.get(i).getDate() %></td>
	      	<td id="tddeliverystate"><%= ordersList.get(i).getDeliverystate() %></td>
	      	<td id="tdorderstate"><%= ordersList.get(i).getOrderstate() %></td>
	      	<td><input id="button1" style="font-size: 16px;" class="button" type="submit" value="修 改"></td>
	      </form>
	      <form action="adminCancelOrders.action" method="post">
	       <input id="in2" type="hidden" name="oid" value="<%= ordersList.get(i).getOid() %>">
   			  <td><input id="button2" style="font-size: 16px;" class="button" type="submit" value="修 改"></td>
   		  </form>
	      </tr>
       <% } %>
    </table>
  </body>
</html>
