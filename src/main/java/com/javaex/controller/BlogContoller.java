package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.service.CategoryService;
import com.javaex.service.PostService;
import com.javaex.service.UserService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="{id}")
public class BlogContoller {
	
	@Autowired
	private UserService userService;
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
	
	//블로그메인
	@RequestMapping(value="", method= {RequestMethod.GET, RequestMethod.POST})
	public String main(@PathVariable("id")String id,
						@RequestParam(value="cateNo", required = false, defaultValue = "0")int cateNo,
						@RequestParam(value="postNo", required = false, defaultValue = "0")int postNo,
						Model model) {
		System.out.println(" BlogCtrl > main");
		
		//블로그의 계정정보 보내기
		UserVo authUser = userService.getUser(id);
		model.addAttribute("authUser", authUser);
		//System.out.println(authUser);
		
		//블로그정보 보내기
		BlogVo authBlog = blogService.getBlog(id);
		model.addAttribute("authBlog", authBlog);
		//System.out.println(authBlog);
		
		//카테고리 내보내기
		List<CategoryVo> cateList = categoryService.getList(id);
		model.addAttribute("cateList", cateList);
		//System.out.println(cateList);
		
		//카테고리별 게시물리스트
		List<PostVo> postListCate = postService.postListCate(cateNo);
		model.addAttribute("postListCate", postListCate);
		
		//카테고리별 최신 게시물 불러오기 (메인)
		PostVo mainPost = postService.getPostCate(cateNo);
		model.addAttribute("mainPost", mainPost);
		
		//게시물 불러오기
		PostVo getPost = postService.getPost(postNo);
		model.addAttribute("getPost", getPost);
		System.out.println(getPost);
		
		return "blog/blog-main";
	}
	
	//기본설정
	@RequestMapping(value="admin/basic", method= {RequestMethod.GET, RequestMethod.POST})
	public String admin(@PathVariable("id")String id, HttpSession session, Model model) {
		System.out.println(" BlogCtrl > admin/basic");
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		String loginId = authUser.getId();
		
		BlogVo authBlog = blogService.getBlog(loginId);
		System.out.println(authBlog);
		model.addAttribute("authBlog", authBlog);
		
		
		return "blog/admin/blog-admin-basic";
	}
	
	//기본설정 변경
	@RequestMapping(value="admin/basicModify", method= {RequestMethod.GET, RequestMethod.POST})
	public String basicModify(@PathVariable("id")String id
							,@RequestParam("blogTitle") String blogTitle
							,@RequestParam(value = "file") MultipartFile file) {
		System.out.println(" BlogCtrl > basicModify");
		
		if(file == null) {
			System.out.println("없음");
		}else {
			System.out.println("사진잇음");
		}
		blogService.basicModify(id, blogTitle, file);
		
		return "redirect:/{id}";
	}
	
	

}
