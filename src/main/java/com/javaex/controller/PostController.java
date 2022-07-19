package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CategoryService;
import com.javaex.service.PostService;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;
import com.javaex.vo.UserVo;

@Controller
public class PostController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
	
	//작성 폼
	@RequestMapping(value="{id}/admin/writeForm")
	public String writeForm(@PathVariable("id")String id, HttpSession session, Model model) {
		System.out.println(" PostCtrl > writeForm");
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		String crtId = authUser.getId();
		
		//카테고리 리스트 넘기기
		List<CategoryVo> categoryList =  categoryService.getList(id);
		model.addAttribute("categoryList", categoryList);
		
		
		
		if(crtId.equals(id)) {
			return "blog/admin/blog-admin-write";
		}else {
			return "error/403";
		}
	}
	
	//작성
	@RequestMapping(value="{id}/admin/write")
	public String write(@ModelAttribute PostVo postVo) {
		System.out.println(" PostCtrl > write");
		
		postService.write(postVo);
		return "redirect:writeForm";
	}
	
	
	//카테고리 내 게시물 리스트
	@RequestMapping(value="postListCate")
	public List<PostVo> postListCate(@RequestParam("cateNo")int cateNo) {
		System.out.println(" PostCtrl > postListCate");
		
		//System.out.println("불로올 CateNo" + cateNo);
		List<PostVo> postListCate = postService.postListCate(cateNo);
		
		
		return postListCate;
	}
	
	
	//아이디 내 최신게시물 가져오기
	@RequestMapping(value="getRecentPost")
	public String getRecentPost() {
		
	}

}
