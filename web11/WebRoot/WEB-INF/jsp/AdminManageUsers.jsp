<%@ page language="java" import="java.util.*, com.company.entity.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理用户</title>
    
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
		#span1{
    		position: absolute;
       		left: 560px;
          	top: 0px;
          	font-size: 40px;
          	background: linear-gradient(to right, red, green, blue);
          	-webkit-background-clip: text;
          	color: transparent;
    	}
		table{
	        border: 1px solid black;
	        position: absolute;
	        left: 80px;
	        top: 140px;
	        text-align: center;
	    }
	    th{
	    	width: 105px;
	    	height: 60px;
	    }
	    tr{
	    	width: 105px;
	    	height: 60px;
	    }
	    td{
	    	width: 105px;
	    	height: 80px;
	    }
	    #tduser{
	    	font-size: 20px;
	    	color: blue;
	    }
	    #tdstate{
	    	font-size: 22px;
	    	color: red;
	    }
	    #tdnumber{
	    	width: 130px;
	    }
	    #next{
	        position: absolute;
	        left: 780px;
	        top: 680px;
	    }
	    #previous{
	        position: absolute;
	        left: 620px;
	        top: 680px;
	    }
	    #number{
	        position: absolute;
	        left: 714px;
	        top: 680px;
	        font-size: 20px;
	        text-align: center;
	    }
	    .button{
	    	width: 60px;
	    	height: 40px;
	    	background: white;
	    }
	    input:hover{
	    	cursor: pointer;
	    	color: red;
	    	border: 2px solid blue;
	    }
	    #label1{
	    	color: red;
	    	font-size: 25px;
	    }
	    .page{
	    	width: 60px;
	    	height: 30px;
	    	background: white;
	    }
    </style>
  </head>
  <script>
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
  <% 
  	 String success = (String) request.getAttribute("success");
     //System.out.println("success: " + success);
     if(success == null){
     	success = "";
     }
     
     List<User> userList = (List<User>) request.getAttribute("userList");
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
  %>

  <body>
    <span id="span1"><br>以下为全部用户资料</span>
   	<label id="label1"><%=success %></label>
    <table border="1" cellspacing="0">
       <tr>
          <th>用户名</th>
          <th>用户密码</th>
          <th>用户手机号</th>
          <th>用户姓名</th>
          <th>用户身份证</th>
          <th>用户生日</th>
          <th>用户地址</th>
          <th>用户创建日期</th>
          <th>用户状态</th>
          <th>修改用户</th>
          <th>注销用户</th>
          <th>恢复用户</th>
       </tr>	   
       <% for(int i = 0; i < userList.size(); i ++) { %>
	       <tr>
	       <form action="adminSkipUpdateUser.action" method="post" target="_blank">
	          <td id="tduser"><%= userList.get(i).getUsername() %></td>
	          <input id="in1" type="hidden" name="username" value="<%= userList.get(i).getUsername() %>">
	          <td><%= userList.get(i).getUserpwd() %></td>
	          <td><%= userList.get(i).getPhone() %></td>
	          <td><%= userList.get(i).getUname() %></td>
	          <td id="tdnumber"><%= userList.get(i).getIDnumber() %></td>
	          <td><%= userList.get(i).getBirthday() %></td>
	          <td><%= userList.get(i).getAddress() %></td>
	          <td><%= userList.get(i).getDate() %></td>
	          <td id="tdstate"><%= userList.get(i).getState() %></td>
	          <td><input class="button" type="submit" value="修 改"></td>
	       </form>
	       <form action="adminDeleteUser.action" method="post">
	       	  <input type="hidden" name="username" value="<%= userList.get(i).getUsername() %>">
   			  <td><input id="button1" class="button" type="submit" value="注 销"></td>
   		   </form>
   		   <form action="adminRecoverUser.action" method="post">
	       	  <input type="hidden" name="username" value="<%= userList.get(i).getUsername() %>">
   			  <td><input id="button2" class="button" type="submit" value="恢 复"></td>
   		   </form>
	       </tr>
       <% } %>
   </table>
   
   <form id="next" action="userNextPage.action" method="post">
       <input id="nextpage" class="page" type="submit" value=<%=nextPage %>>
   </form>
   <label id="number"><%=currentPage %>/${allPage}</label>
   <form id="previous" action="userPreviousPage.action" method="post">
       <input id="previouspage" class="page" type="submit" value=<%=previousPage %>>
   </form>
    
  </body>
</html>
