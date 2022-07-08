package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;

@Repository
public class CategoryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//회원가입 시 기본카테고리 생성
	public int join(CategoryVo categoryVo) {
		System.out.println(" CategoryDao > join");
		
		return sqlSession.insert("category.join", categoryVo);
	}

}
