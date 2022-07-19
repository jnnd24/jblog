package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.PostDao;
import com.javaex.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private PostDao postDao;
	
	//작성
	public int write(PostVo postVo) {
		System.out.println(" PostService > write");
		
		return postDao.write(postVo);
	}

	

	//카테고리 내 게시물 리스트
	public List<PostVo> postListCate(int cateNo) {
		System.out.println(" PostService > postListCate");
		
		List<PostVo> postListCate = postDao.postListCate(cateNo);
		
		return postListCate;
	}
	
	//카테고리 최신글 불러오기
	public PostVo getPostCate(int cateNo) {
		System.out.println(" PostService > getPostCate");
		
		PostVo mainPost = postDao.getPostCate(cateNo);
		
		return mainPost;
	}
	
	//게시물 불러오기
	public PostVo getPost(int postNo) {
		System.out.println(" PostService > getPost");
		
		PostVo getPost = postDao.getPost(postNo);
		
		return getPost;
	}
	
	//최신게시물 불러오기
	public PostVo getRecentPost(String id) {
		System.out.println(" PostService > getRecentPost");
		
		//최신글의 postno구하기
		int rePostNo = postDao.getRecentPostNo(id);
		
		//게시물 불러오기
		PostVo rePost = postDao.getPost(rePostNo);
		
		return rePost;
	}
	
}
