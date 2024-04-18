package com.yedam.app.upload.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

		@GetMapping("upload") // classpath:/temploates/upload.html
		public void getUploadPage() {}
//				└>요청 경로를 그대로 반환하므로 요청경로=호출html명이 된다.
		
		@PostMapping("formUpload")
		public String formUploadFile(@RequestPart MultipartFile[] images) {
			for(MultipartFile image : images) {
				if(image.getContentType().startsWith("image") == false) {
						System.out.println("No Image");
					return null;
				}
				String originalName = image.getOriginalFilename();
				System.out.println("original : " + originalName);
				String fileName = originalName.substring(originalName.lastIndexOf("//")+1);
				System.out.println("fileName : " + fileName);
				
				String uploadPath = "C:\\upload";
				String saveName = uploadPath + File.separator + fileName;
//								  C:\\upload +      \\		  + 파일명
				System.out.println("saveName : " + saveName);
				Path savePath = Paths.get(saveName);
				
				try {
					image.transferTo(savePath); //파일저장 메서드
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return "upload";
		}
		
}
