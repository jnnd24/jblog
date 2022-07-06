package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	//회원가입
	public int join(UserVo userVo) {
		System.out.println(" UserService > join");
		
		return userDao.join(userVo);
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
