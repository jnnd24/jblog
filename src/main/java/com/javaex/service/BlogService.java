package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
