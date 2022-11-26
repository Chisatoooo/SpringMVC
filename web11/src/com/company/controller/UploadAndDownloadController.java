package com.company.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
public class UploadAndDownloadController {
	@RequestMapping("/uploadController.action")
	public void uploadController(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String path = "D:\\Homework\\image\\";
		//获取当前时间
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		String now = sdf.format(date);
		//获取源文件名称
		String fileName = file.getOriginalFilename();
		String[] str = fileName.split("\\.");
		String newFile = path + now + "." + str[1];
		File fPath = new File(newFile);
		try {
			file.transferTo(fPath);
			String success = "修改成功!";
			request.setAttribute("fPath", fPath.toString());
			request.setAttribute("success", success);
			request.getRequestDispatcher("Upload.jsp").forward(request, response);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/downloadController.action")
	public ResponseEntity<byte[]> export(String fileName,String filePath) throws IOException {  
    	//资源信息
    	fileName = "1.jpg";
    	filePath= "D:\\Homework\\image\\" + fileName;
    	
        HttpHeaders headers = new HttpHeaders();    
        File file = new File(filePath);
        
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);    
        headers.setContentDispositionFormData("attachment", fileName);    
       
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
                                              headers, HttpStatus.CREATED);    
    }
}
