package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.vo.FileVo;

@Service
public class FileService {
	
	
	public String save(MultipartFile file) {
		System.out.println(" FileService > save");

		String saveDir ="C:\\javaStudy\\upload";
		
		//파일저장을위한 정보추출
		
		//파일이름
		String orgName = file.getOriginalFilename();
		
		//확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		
		//저장파일명
		String saveName = System.currentTimeMillis()+UUID.randomUUID().toString()+exName;
		
		//파일경로
		String filePath = saveDir + "\\" + saveName;
		
		//파일사이즈
		long fileSize = file.getSize();
		
		FileVo fileVo = new FileVo(orgName, saveName, filePath, fileSize);
		System.out.println(fileVo);
		
		
		//DB저장 -> 과제
		
		
		//(2)파일 저장
		try {
			byte[] fileData = file.getBytes();
			
			OutputStream os = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			
			bos.write(fileData);
			bos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return saveName;
		
	}
	
	

	

}
