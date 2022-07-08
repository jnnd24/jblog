package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;
	
	//회원가입
	public int join(UserVo userVo) {
		System.out.println(" UserService > join");
		
		System.out.println(userVo);
		
		//계정 생성
		userDao.join(userVo);
		System.out.println("계정생성 완료");
		
		//블로그 생성
		BlogVo blogVo = new BlogVo();
		blogVo.setId(userVo.getId());
		blogVo.setBlogTitle(userVo.getUserName() + "의 블로그입니다.");
		blogVo.setLogoFile("");
		
		blogDao.join(blogVo);
		System.out.println("블로그생성완료");
		
		//기본 카테고리 생성
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setId(userVo.getId());
		categoryVo.setCateName("미분류");
		categoryVo.setDescription("기본으로 만들어지는 카테고리입니다.");
		
		categoryDao.join(categoryVo);
		System.out.println("카테고리생성완료");
		 
		return 1;
	}
	
	//ID중복체크
	public String idcheck(String id) {
		System.out.println(" UserService > idcheck");
		
		int count = userDao.idcheck(id);
		String result;
		
		if(count==0) {
			result = "ok";
		}else {
			result = "no";
		}
		
		return result;
	}

	
	
	//로그인 정보 불러오기
	public UserVo getUser(UserVo userVo) {
		System.out.println(" UserService > getUser");
		
		UserVo authUser = userDao.getUser(userVo);
		
		return authUser;
	}
}
