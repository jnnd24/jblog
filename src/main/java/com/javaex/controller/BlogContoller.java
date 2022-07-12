package com.javaex.controller;

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
import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="{id}")
public class BlogContoller {
	
	@Autowired
	private BlogService blogService;
	
	//블로그메인
	@RequestMapping(value="", method= {RequestMethod.GET, RequestMethod.POST})
	public String main(@PathVariable("id")String id, Model model) {
		System.out.println(" BlogCtrl > main");
		
		//id보내기
		model.addAttribute("id", id);
		
		return "blog/blog-main";
	}
	
	//기본설정
	@RequestMapping(value="admin/basic", method= {RequestMethod.GET, RequestMethod.POST})
	public String admin(HttpSession session, Model model) {
		System.out.println(" BlogCtrl > admin/basic");
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		String id = authUser.getId();
		
		BlogVo authBlog = blogService.getBlog(id);
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
