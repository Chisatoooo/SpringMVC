<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户登录</title>
    
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
			top: 180px;
			left: 570px;
			background: url("image/background2.jpg");
			background-size: cover;
		}
		#div1{
			border: 1px solid gray;
			width: 400px;
			height: 280px;
			position: absolute;
			top: 60px;
			left: -25px;
			background: #409eff17;
			border-radius: 5%;
		}
		.box{
			width: 350px;
			height: 40px;
		}
		.button{
			width: 50px;
			height: 40px;
		}
		.button:hover{
			cursor: pointer;
			opacity: 60%;
		}
		#button1{
			width: 150px;
			height: 40px;
			position: absolute;
			top: 270px;
			left: 0px;
			background: #00a7de;
			color: white;
			font-size: 20px;
			border: 1px solid gray;
			border-radius: 3%;
		}
		#button2{
			width: 150px;
			height: 40px;
			position: absolute;
			top: 270px;
			left: 200px;
			background: white;
			color: #665758;
			font-size: 20px;
			border: 1px solid #ccc;
			border-radius: 3%;
		}
		#username{
			position: absolute;
			top: 100px;
			left: 0px;
		}
		#userpwd{
			position: absolute;
			top: 180px;
			left: 0px;
		}
		#a1{
			width:60px;
			position: absolute;
			top: 306px;
			left: 205px;
			font-size: 13px;
			text-decoration: none;
		}
		#a2{
			width:60px;
			position: absolute;
			top: 306px;
			left: 285px;
			font-size: 13px;
			text-decoration: none;
		}
		#a1:hover{
			cursor: pointer;
			text-decoration: underline;
		}
		#a2:hover{
			cursor: pointer;
			text-decoration: underline;
		}
		span{
			width: 200px;
			height: 50px;
			position: absolute;
			top: -10px;
			left: 80px;
		}
		#result1{
			color: red;
			width: 200px;
			height: 30px;
			position: absolute;
			top: 145px;
			left: 0px;
		}
		#result2{
			color: red;
			width: 200px;
			height: 30px;
			position: absolute;
			top: 225px;
			left: 0px;
		}
    </style>
    <% 
    
    %>
  </head>
  <script>
  	/*
  	function checkData(){
  		var username = document.forms["form1"]["username"].value;
  		var userpwd = document.forms["form1"]["userpwd"].value;
  		if(username == null || username == ""){
  			alert("请正确输入用户名!");
  			return false;
  		}else if(userpwd == null || userpwd == ""){
  			alert("请正确输入密码!");
  			return false;
  		}
  		return true;
  	}
	*/
  	window.onload = function(){
    	var oButton1 = document.getElementById("button1");
    	var oResult1 = document.getElementById("result1");
    	var oResult2 = document.getElementById("result2");
    	oButton1.onclick = function(){
    	//ajax
    	var xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP'); 
              xhr.open("POST","userLogin.action?" + "username=" + username.value + "&" + "userpwd=" + userpwd.value, true);
              xhr.onreadystatechange = function(){
                if(xhr.readyState == 4){
                    if(xhr.status == 200){
                        var result = xhr.responseText;
                        if(result == "1"){
                        	oResult1.innerHTML = "";
                        	oResult2.innerHTML = "";
                        	alert("登陆成功, 点击确定后跳转页面.");
                        	window.location.href = "userLoginSkip.action?" + "username=" + username.value;
                        }
                        if(result == "2"){
                        	//alert("密码错误!");
                        	oResult1.innerHTML = "";
                        	oResult2.innerHTML = "密码错误!";
                        }
                        if(result == "3"){
                        	//alert("该用户不存在!");
                        	oResult1.innerHTML = "该用户不存在!";
                        	oResult2.innerHTML = "";
                        }
                        if(result == "4"){
                        	//alert("该用户已注销!");
                        	oResult1.innerHTML = "该用户已注销!";
                        	oResult2.innerHTML = "";
                        }
                        if(result == "0"){
                        	alert("请输入用户名!");
                        }
                        if(result == "00"){
                        	alert("请输入密码!");
                        }
                    }
                }
            }
            xhr.send();
       }
   }
  </script>
  <body>
   <span style="font-size: 38px; color: white;">用 户 登 录</span>
   <div id="div1"></div>
   <form id="form1">
 		<input id="username" class="box" type="text" name="username" autocomplete="off" placeholder=" 请输入帐号/用户名"/><br>
 		<input id="userpwd" class="box" type="password" name="userpwd" autocomplete="off" placeholder=" 请输入密码"/><br>
 		<input id="button1" class="button" type="button" value="登 录"/>
 		<label id="result1"></label>
 		<label id="result2"></label>
   </form>
   <form id="form2" action="UserRegister.jsp" method="post">
   		<input id="button2" class="button" type="submit" value="注 册"/>
   </form>
   <!-- 
   <a id="a1" href="">忘记密码?</a>
   <a id="a2" href="">忘记帐号?</a>
    -->
   
  </body>
</html>
