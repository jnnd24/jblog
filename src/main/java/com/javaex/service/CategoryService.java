package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CategoryDao;
import com.javaex.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	
	//카테고리 불러오기
	public List<CategoryVo> getList(String id) {
		System.out.println(" CategoryService > getList");
		
		List<CategoryVo> categoryList = categoryDao.getList(id);
		
		return categoryList;
	}
	
	
	//카테고리 추가
	public String add(CategoryVo categoryVo) {
		System.out.println(" CategoryService > add");
		
		categoryDao.add(categoryVo);
		
		return "";
	}

}
