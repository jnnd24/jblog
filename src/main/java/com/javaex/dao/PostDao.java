package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PostVo;

@Repository
public class PostDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//글 작성
	public int write(PostVo postVo) {
		System.out.println(" PostDao > write");
		
		return sqlSession.insert("post.write", postVo);
	}

	
	//카테고리 내 게시물 리스트
	public List<PostVo> postListCate(int cateNo) {
		
		List<PostVo> postListCate = sqlSession.selectList("post.postListCate", cateNo);
		
		return postListCate;
	}
	
	public PostVo getPostCate(int cateNo) {
		System.out.println(" PostDao > getPostCate");
		
		PostVo mainPost = sqlSession.selectOne("post.getPostCate", cateNo);
		
		return mainPost;
	}
	
	//게시물 불러오기
	public PostVo getPost(int postNo) {
		System.out.println(" PostDao > getPost");
		
		PostVo getPost = sqlSession.selectOne("post.getPost", postNo);
		
		return getPost;
	}
	
	
	//최신게시물 불러오기
	public PostVo getRecentPost(String id) {
		System.out.println(" PostDao > getPost");
		
		PostVo getRecentPost = sqlSession.selectOne("post.getRecentPost", id);
		
		return getRecentPost;
	}
	
	
	//최신글의 postNo 구하기
	public int getRecentPostNo(String id) {
		System.out.println(" PostDao > getRecentPostNo");
		
		int rePostNo = sqlSession.selectOne("post.getRecentPostNo", id);
		
		return rePostNo;
	}
}
