package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//회원가입 시 기본 블로그 생성
	public int join(BlogVo blogVo) {
		System.out.println(" BlogDao > join");
		
		sqlSession.insert("blog.join", blogVo);
		
		return 1;
	}
	
	
	//블로그정보 불러오기
	public BlogVo getBlog(String id) {
		System.out.println(" BlogDao > getBlog");
		
		BlogVo authBlog = sqlSession.selectOne("blog.getBlog", id);
		
		return authBlog;
	}

}
