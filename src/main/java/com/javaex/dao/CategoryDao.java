package com.javaex.dao;

import java.util.List;

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
	
	//카테고리수정에 리스트 가져오기
	public List<CategoryVo> getList(String id) {
		System.out.println(" CategoryDao > getList");
		
		List<CategoryVo> categoryList = sqlSession.selectList("category.getList", id);
		
		return categoryList;
	}
	
	//카테고리 추가
	public int add(CategoryVo categoryVo) {
		System.out.println(" CategoryDao > add");
		
		return sqlSession.insert("category.add", categoryVo);
	}

}
