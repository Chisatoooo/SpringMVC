<%@ page language="java" import="java.util.*, com.company.entity.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理商品</title>
    
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
    	#span2{
    		position: fixed;
    		right: 20px;
    		top: 10x;
    		color: red;
    	}
		table{
	        border: 1px solid black;
	        position: absolute;
	        left: 130px;
	        top: 200px;
	        text-align: center;
	    }
	    th{
	    	width: 120px;
	    	height: 60px;
	    }
	    #thname{
	    	width: 180px;
	    }
	    #thimg{
	    	width: 220px;
	    }
	    tr{
	    	width: 120px;
	    	height: 60px;
	    }
	    td{
	    	width: 120px;
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
	    #tdstate{
	    	font-size: 25px;
	    	color: red;
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
	        left: 630px;
	        top: 1000px;
	    }
	    #number{
	        position: absolute;
	        left: 724px;
	        top: 1000px;
	        font-size: 20px;
	        text-align: center;
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
	    #addbutton{
	    	position: absolute;
	    	width: 120px;
	    	height: 50px;
	        left: 1080px;
	        top: 100px;
	        border: 2px solid black;
	        border-radius: 10%;
	        font-size: 20px;
	    }
	    #addbutton:hover{
	    	border: 2px solid blue;
	    	color: red;
	    	transform: scale(1.1);
	    }
	    #label1{
	    	color: red;
	    	font-size: 25px;
	    }
    </style>
  </head>
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
  		
  		var oButton2 = document.getElementById("button2");
  		var oButton3 = document.getElementById("button3");
  		var oTdstate = document.getElementById("tdstate");
  		if(oTdstate.innerHTML == "已下架"){
  			oButton2.setAttribute("disabled", false);
  			oButton2.style.cursor = "not-allowed";
  			oButton2.style.color = "#c2c2c2";
  			oButton2.style.border = "none";
  		}
  		if(oTdstate.innerHTML == "已上架"){
  			oButton3.setAttribute("disabled", false);
  			oButton3.style.cursor = "not-allowed";
  			oButton3.style.color = "#c2c2c2";
  			oButton3.style.border = "none";
  		}
  		
  	}
  </script>
  <% 
  	 List<Product> productList = (List<Product>) request.getAttribute("productList");
     String picture = (String) request.getAttribute("picture");
     String username = (String) application.getAttribute("username");
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
 
  
  <body>
  	<span id="span1">以下为全部商品</span>
  	<label id="label1"><%=success %></label>
  	<form id="form1" action="adminQueryProduct.action" method="post" onsubmit="return checkData()">
  		<input id="querytext" type="text" name="producttext" autocomplete="off" placeholder=" 请输入商品关键字 （以价格降序排列）"/>
  		<input id="querybutton" class="button" type="submit" value="搜 索">
  	</form>
  	<form id="form2" action="adminSkipAddProduct.action" method="post">
  		<input id="addbutton" class="button" type="submit" value="添加商品">
  	</form>
    <table id="table1" border="1" cellspacing= "0">
       <tr>
          <th>商品编号</th>
          <th id="thname">商品名称</th>
          <th id="thimg">商品图片</th>
          <th>商品价格</th>
          <th>商品类型</th>
          <th>商品状态</th>
          <th>修改商品价格</th>
          <th>下架商品</th>
          <th>恢复商品</th>
       </tr>
       <% for(int i = 0; i < productList.size(); i ++){ %>
	      <tr>
	      <form action="adminSkipUpdateProduct.action" method="post">
	      	<td id="tdid"><%= productList.get(i).getId() %></td>
	      	<input type="hidden" name="id" value="<%= productList.get(i).getId() %>">
	      	<td id="tdname"><%= productList.get(i).getName() %></td>
	      	<td><img src=<%="/picture/" + productList.get(i).getImage() %>></td>
	      	<td id="tdprice">￥ <%= productList.get(i).getPrice() %></td>
	      	<td id="tdtype"><%= productList.get(i).getType() %></td>
	      	<td id="tdstate"><%= productList.get(i).getState() %></td>
	      	<td><input id="button1" style="font-size: 16px;" class="button" type="submit" value="修 改"></td>
	      </form>
	      <form action="adminDeleteProduct.action" method="post">
	       	<input type="hidden" name="id" value="<%= productList.get(i).getId() %>">
   			<td><input id="button2" style="font-size: 16px;" class="button" type="submit" value="下 架"></td>
   			<!-- <script type="text/javascript">
   				var state = "<%= productList.get(i).getState() %>";
   				//alert(state);
	      		var oButton = document.getElementsByClassName("button");
	      		//alert(oButton.value);
		  		if(state == "已下架"){
		  			oButton.setAttribute("disabled", false);
		  			oButton.style.cursor = "not-allowed";
		  			oButton.style.color = "#c2c2c2";
		  			oButton.style.border = "none";
		  		}
		  		
	      	</script> -->
   		  </form>
   		  <form action="adminRecoverProduct.action" method="post">
	       	<input type="hidden" name="id" value="<%= productList.get(i).getId() %>">
   			<td><input id="button3" style="font-size: 16px;" class="button" type="submit" value="恢 复"></td>
   			<!-- <script type="text/javascript">
	      		var state = "<%= productList.get(i).getState() %>";
   				//alert(state);
	      		var oButton = document.getElementsByClassName("button");
		  		if(state == "已上架"){
		  			oButton.setAttribute("disabled", false);
		  			oButton.style.cursor = "not-allowed";
		  			oButton.style.color = "#c2c2c2";
		  			oButton.style.border = "none";
  				}
	      	</script> -->
   		  </form>
	      </tr>
       <% } %>
    </table>
    
   <form id="next" action="adminNextProductPage.action" method="post">
       <input id="nextpage" class="page" type="submit" value=<%=nextPage %>>
   </form>
   <label id="number"><%=currentPage %>/${allPage}</label>
   <form id="previous" action="adminPreviousProductPage.action" method="post">
       <input id="previouspage" class="page" type="submit" value=<%=previousPage %>>
   </form>
    
  </body>
</html>
