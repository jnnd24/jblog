package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;
	
	/*
	//회원가입 시 기본블로그 생성
	public int getBlog(UserVo userVo) {
		System.out.println(" BlogService > join");
		
		return blogDao.join();
	}
	 */
	
	
	//블로그정보 가져오기
	public BlogVo getBlog(String id) {
		System.out.println(" BlogService > getBlog");
		
		BlogVo authBlog = blogDao.getBlog(id);
		
		return authBlog;
	}
	
	
	//기본정보 수정하기
	public int basicModify(String id, String blogTitle, MultipartFile file) {
		System.out.println(" BlogService > basicModify");
		
		BlogVo blogVo = new BlogVo();
		blogVo.setId(id);
		blogVo.setBlogTitle(blogTitle);
		
		
		
		//사진이있는 경우 사진변경
		if(file != null) {
			System.out.println("사진있을 때");
			//파일이름
			String orgName = file.getOriginalFilename();
			
			//확장자명
			String exName = orgName.substring(orgName.lastIndexOf("."));

			//저장파일명
			String saveName = System.currentTimeMillis()+UUID.randomUUID().toString()+exName;
			
			//수정하기
			//blogDao.basicModify(blogTitle, saveName);
			
			//저장위치
			String saveDir = "C:\\javaStudy\\jblog";
			
			//파일전체경로
			String filepath = saveDir + "\\" + saveName;
			
			//쿼리에 이름넣기
			blogVo.setLogoFile(saveName);
			
			//파일 저장하기
			try {
				byte[] filedata = file.getBytes();
				
				OutputStream os = new FileOutputStream(filepath);
				BufferedOutputStream bos = new BufferedOutputStream(os);
				bos.write(filedata);
				bos.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else{
			System.out.println("사진없을때");
		}
		
		blogDao.basicModify(blogVo);
		
		return 1;
	}
}
