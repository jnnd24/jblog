package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;

	//회원가입
	public int join(UserVo userVo) {
		System.out.println(" UserDao > join");
		
		return sqlSession.insert("users.join", userVo);
	}
	
	//ID중복체크
	public int idcheck(String id) {
		System.out.println(" UserDao > idcheck");
		
		int result = sqlSession.selectOne("users.idcheck", id);
		
		return result;
	}
	
	
	//로그인 정보 불러오기
	public UserVo getUser(UserVo userVo) {
		
		UserVo authUser = sqlSession.selectOne("users.getUser", userVo);
		System.out.println(authUser);
		
		return authUser;
	}
	
}
