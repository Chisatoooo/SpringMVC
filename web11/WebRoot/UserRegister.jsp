<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- <embed id="embed" src="1.mp3" hidden="true" autostart="true" loop="true" color="white"></embed> -->
  <style>
  		input::-webkit-input-placeholder {
        	color: #ccc;
      	}
  		body{
			width: 200px;
			height: 300px;
			position: absolute;
			top: 50px;
			left: 590px;
			background: url("image/background3.jpg");
			background-size: cover;
		}
		#div1{
			position: absolute;
			top: 0px;
			left: -580px;
		}
		.title-line {
		    width: 1500px;
		    border-bottom: 1px solid #ccc;
		    margin-bottom: 28px;
		    text-align: center;
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
			opacity: 80%;
		}
		#b1{
			width: 350px;
			height: 40px;
			position: absolute;
			top: 450px;
			left: 0px;
			background: #00a7de;
			color: white;
			font-size: 20px;
			border: 1px solid gray;
		}
		#username{
			position: absolute;
			top: 80px;
			left: 0px;
		}
		#userpwd{
			position: absolute;
			top: 140px;
			left: 0px;
		}
		#phone{
			position: absolute;
			top: 200px;
			left: 0px;
		}
		#getcode{
			position: absolute;
			top: 260px;
			left: 230px;
			width: 120px;
			height: 40px;
			background: white;
			border: 1px solid grey;
		}
		#getcode:hover{
			cursor: pointer;
			color: red;
		}
		#code{
			position: absolute;
			top: 260px;
			left: 0px;
			width: 150px;
			height: 40px;
		}
		#uname{
			position: absolute;
			top: 320px;
			left: 0px;
		}
		#IDnumber{
			position: absolute;
			top: 380px;
			left: 0px;
		}
		input:hover{
			opacity: 70%;
		}
		#a1{
			width: 130px;
			height: 20px;
			position: absolute;
			top: 500px;
			left: 220px;
			font-size: 13px;
			color: #1aa6d7;
			background: none;
			border: none;
			text-decoration: none;
		}
		#a1:hover{
			cursor: pointer;
			text-decoration: underline;
		}
		#media{
			position: absolute;
			left: 20px;
			bottom: -400px;
		}
		#label{
			width: 90px;
			position: absolute;
			left: 130px;
			bottom: 10px;
			color: red;
		}
  </style>
  </head>
  <%
  	 String music = "music.mp3";
   %>
  <script>
  	/*
	function checkData(){
		var username = document.forms["form1"]["username"].value;
		var userpwd = document.forms["form1"]["userpwd"].value;
		var phone = document.forms["form1"]["phone"].value;
		var code = document.forms["form1"]["code"].value;
		var name = document.forms["form1"]["name"].value;
		var IDnumber = document.forms["form1"]["name"].value;
		//判断
  		if(username == null || username == ""){
  			alert("请正确输入用户名!");
  			return false;
  		}else if(userpwd == null || userpwd == ""){
  			alert("请正确输入密码!");
  			return false;
  		}else if(phone == null || phone == ""){
  			alert("请正确输入手机号!");
  			return false;
  		}else if(code == null || code == ""){
  			alert("请正确输入验证码!");
  			return false;
  		}else if(name == null || name == ""){
  			alert("请正确输入姓名!");
  			return false;
  		}else if(IDnumber == null || IDnumber == ""){
  			alert("请正确输入身份证!");
  			return false;
  		}
  		return true;
  	}
  	*/
    window.onload = function(){
    	var time = 60;
    	var oGetCode = document.getElementById("getcode");
    	var oLabel = document.getElementById("label");
    	var oPhone = document.getElementById("phone");
    	oGetCode.setAttribute("disabled", false);
    	oGetCode.style.cursor = "not-allowed";
  		oGetCode.style.color = "#c2c2c2";
  		oGetCode.style.border = "none";

    	oPhone.onblur = function(){
    		var xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP'); 
              xhr.open("POST","checkGetCode.action?" + "phone=" + phone.value, true);
              xhr.onreadystatechange = function(){
                if(xhr.readyState == 4){
                    if(xhr.status == 200){
                        var getResult = xhr.responseText;
                        if(getResult == "1"){
                        	oGetCode.removeAttribute("disabled", false);
                        	oGetCode.style.cursor = "pointer";
                        	oGetCode.style.color = "black";
  							oGetCode.onmouseout = function(){
  								oGetCode.style.color = "black";
  							}
  							oGetCode.onmousemove = function(){
  								oGetCode.style.color = "red";
  							}
  							oGetCode.style.border = "1px solid grey";
                        }
                        if(getResult == "4"){
                        	oGetCode.setAttribute("disabled", false);
                        	oGetCode.style.cursor = "not-allowed";
  							oGetCode.style.color = "#c2c2c2";
  							oGetCode.style.border = "none";
                        }
                    }
                }
            }
            xhr.send();
    	}
    	
    	oGetCode.onclick = function(){
    	//ajax
    	var xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP'); 
              xhr.open("POST","getPhoneCode.action?" + "phone=" + phone.value, true);
              xhr.onreadystatechange = function(){
                if(xhr.readyState == 4){
                    if(xhr.status == 200){
                        
                    }
                }
            }
            xhr.send();
    	
    		//开启定时器
    		oGetCode.setAttribute("disabled", true);
			oGetCode.value = "重新发送(" + time + ")"; 
            var timer = setInterval(function () {
				time--;
				oGetCode.value = "重新发送(" + time + ")";
            	if (time == 0) {
            		oGetCode.removeAttribute("disabled", false);
					oGetCode.value = "获取手机验证码";
                	//关闭定时器
                	clearInterval(timer);
                	time = 60;
                }
            }, 1000);
    	}
		
    	
    	var oB = document.getElementById("b1");
    	oB.onclick = function(){
    	//ajax
    	var xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP'); 
              xhr.open("POST","userRegister.action?" + "username=" + username.value + "&" + "userpwd=" + userpwd.value + "&" + "phone=" + phone.value + "&" + "code=" + code.value + "&" + "uname=" + uname.value + "&" + "IDnumber=" + IDnumber.value, true);
              xhr.onreadystatechange = function(){
                if(xhr.readyState == 4){
                    if(xhr.status == 200){
                        var registerResult = xhr.responseText;
                        if(registerResult == "1"){
                        	alert("注册成功, 点击确定后跳转登录页面.");
                        	window.location.href = "UserLogin.jsp";
                        }
                        if(registerResult == "2"){
                        	alert("请正确填写数据!");
                        }
                        if(registerResult == "3"){
                        	alert("该帐号已存在, 请重新注册!");
                        }
                        if(registerResult == "5"){
                        	alert("验证码错误, 请重新输入验证码!");
                        }
                    }
                }
            }
            xhr.send();
    	}
    }
  </script>
  <body>
   <div id="div1" class="title-line">
   		<span style="font-size: 38px;">注 册</span>
   </div>
   <form id="form1" action="" method="post">
   		<input id="username" class="box" type="text" name="username" autocomplete="off" placeholder=" 帐号/用户名（首位为字母 6-10位）"/><br>
   		<input id="userpwd" class="box" type="password" name="userpwd" autocomplete="off" placeholder=" 密码 （8-20位 区分大小写）"/><br>
		<input id="phone" class="box" type="text" name="phone" oninput="value=value.replace(/[^\d]/g,'')" autocomplete="off" placeholder=" 填写常用手机号"/><br>
   		<input id="code" class="box" type="text" name="code" autocomplete="off" placeholder=" 输入短信验证码" />
   		<input id="uname" class="box" type="text" name="uname" autocomplete="off" placeholder=" 姓名（2-6位中文或2-20位英文）"/><br>
   		<input id="IDnumber" class="box" type="text" name="IDnumber" oninput="value=value.replace(/[^\d]/g,'')" autocomplete="off" placeholder=" 身份证（15或18位）"/><br>
   		<input id="b1" class="button" type="button" value="注 册" />
   </form>
   <!-- <form id="form3" action="" method="post"> -->
   		<input id="getcode" type="button" value="获取手机验证码" />
   		<label id="label"></label>
   <!-- </form> -->
   <form id="form2" action="UserLogin.jsp" method="post">
   		<input id="a1" class="button" type="submit" value="已有账号，直接登录>"/>
   </form>
   <audio id="media" src=<%="/music/" + music %> controls="controls" autoplay="autoplay"/>
  </body>
</html>
