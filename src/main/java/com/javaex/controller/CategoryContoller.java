package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CategoryService;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.UserVo;

@Controller
public class CategoryContoller {
	
	@Autowired
	private CategoryService categoryService;
	
	
	//카테고리관리 페이지
	@RequestMapping(value="{id}/admin/category", method= {RequestMethod.GET, RequestMethod.POST})
	public String category(@PathVariable("id")String id, HttpSession session) {
		System.out.println(" BlogCtrl > admin/category");
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		String crtId = authUser.getId();
		
		if(crtId.equals(id)) {
			return "blog/admin/blog-admin-cate";
		}else {
			return "error/403";
		}
		
	
		
		
		
		
	}
	
	//카테고리 리스트
	@ResponseBody
	@RequestMapping(value="admin/categoryList", method= {RequestMethod.GET, RequestMethod.POST})
	public List<CategoryVo> categoryList(HttpSession session) {
		
		//카테고리가져오기
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		String crtId = authUser.getId();
		
		List<CategoryVo> categoryList = categoryService.getList(crtId);
		
		return categoryList;
	}
	
	
	//카테고리 추가
	@ResponseBody
	@RequestMapping(value="admin/categoryAdd", method= {RequestMethod.GET, RequestMethod.POST})
	public String categoryModify(HttpSession session,
								@ModelAttribute CategoryVo categoryVo) {
		System.out.println(" BlogCtrl > admin/categoryAdd");
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		String crtId = authUser.getId();
		
		//아이디등록
		categoryVo.setId(crtId);
		
		//서비스보내기
		String state = categoryService.add(categoryVo);
		
		return state;
	}
	
	
	//카테고리 삭제
	@ResponseBody
	@RequestMapping(value="admin/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String categoryDelete(@ModelAttribute("cateNo")int cateNo) {
		System.out.println(" CategoryCtrl > admin/delete");
		
		System.out.println("delete"+cateNo);
		
		return "";
	}
	
	
	
	
}
