<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理员登录</title>
    
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
		#b1{
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
		#b2{
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
		#adminname{
			position: absolute;
			top: 100px;
			left: 0px;
		}
		#adminpwd{
			position: absolute;
			top: 180px;
			left: 0px;
		}
		span{
			width: 250px;
			height: 50px;
			position: absolute;
			top: -10px;
			left: 60px;
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
  		var adminname = document.forms["form1"]["adminname"].value;
  		var adminpwd = document.forms["form1"]["adminpwd"].value;
  		if(adminname == null || adminname == ""){
  			alert("请正确输入用户名!");
  			return false;
  		}else if(adminpwd == null || adminpwd == ""){
  			alert("请正确输入密码!");
  			return false;
  		}
  		return true;
  	}
  	*/
  	window.onload = function(){
    	var oB1 = document.getElementById("b1");
    	var oResult1 = document.getElementById("result1");
    	var oResult2 = document.getElementById("result2");
    	oB1.onclick = function(){
    	//ajax
    	var xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP'); 
              xhr.open("POST","adminLogin.action?" + "adminname=" + adminname.value + "&" + "adminpwd=" + adminpwd.value, true);
              xhr.onreadystatechange = function(){
                if(xhr.readyState == 4){
                    if(xhr.status == 200){
                        var result = xhr.responseText;
                        if(result == "1"){
                        	oResult1.innerHTML = "";
                        	oResult2.innerHTML = "";
                        	alert("登陆成功, 点击确定后跳转页面.");
                        	window.location.href = "adminLoginSkip.action?" + "adminname=" + adminname.value;
                        }
                        if(result == "2"){
                        	//alert("密码错误!");
                        	oResult1.innerHTML = "";
                        	oResult2.innerHTML = "密码错误!";
                        }
                        if(result == "3"){
                        	//alert("该用户不存在!");
                        	oResult1.innerHTML = "管理员帐号不存在!";
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
   <span style="font-size: 38px; color: white;">管 理 员 登 录</span>
   <div id="div1"></div>
   <form id="form1">
 		<input id="adminname" class="box" type="text" name="adminname" autocomplete="off" placeholder=" 请输入帐号"/><br>
 		<input id="adminpwd" class="box" type="password" name="adminpwd" autocomplete="off" placeholder=" 请输入密码"/><br>
 		<input id="b1" class="button" type="button" value="登 录"/>
 		<label id="result1"></label>
 		<label id="result2"></label>
 		<input id="b2" class="button" type="reset" value="清 除"/>
   </form>
  </body>
</html>
