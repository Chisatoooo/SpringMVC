<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>上传</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		#img{
			width: 200px;
			height: 200px;
			position: absolute;
			top: 30px;
			left: 260px;
			border: 1px solid black;
			border-radius: 50%;
		}
		#file{
			position: absolute;
			top: 250px;
			left: 280px;
		}
		#file:hover{
			cursor: pointer;
			color: red;
		}
		#button{
			width: 160px;
			height: 40px;
			position: absolute;
			top: 320px;
			left: 280px;
		}
		#result{
			width: 90px;
			height: 40px;
			position: absolute;
			top: 370px;
			left: 320px;
			font-size: 20px;
			color: red;
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
	</style>
  </head>
  <script type="text/javascript">
  function checkData(){
		var file = document.forms["form1"]["file"].value;
		//判断
  		if(file == null || file == ""){
  			alert("请选择图片!");
  			return false;
  		}
  		return true;
  	}
  	/*
  	window.onload = function(){
    	var oButton = document.getElementById("button");
    	var oImg = document.getElementById("img");
    	oButton.onclick = function(){
    	//ajax
    	var xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP'); 
              xhr.open("POST","uploadController.action?" + "file=" + file.value, true);
              xhr.onreadystatechange = function(){
                if(xhr.readyState == 4){
                    if(xhr.status == 200){
                        var result = xhr.responseText;
                        if(result == "1"){
                        	alert("修改成功!");
                        }
                        if(result == "2"){
                        	alert("图片过大!");
                        }
                        if(result == "3"){
                        	alert("请选择图片!");
                        }
                    }
                }
            }
            xhr.send();
     	}
    }
    */
  </script>
  <body>
    <form id="form1" action="uploadController.action" method="post" enctype="multipart/form-data" onsubmit="return checkData()">
       <input id="file" type="file" name="file"><br>
       <input id="button" class="button" type="submit" value="修改头像">
    </form>
    <label id="result">${success}</label>
    <img id="img" src="${fPath}">
  </body>
</html>
