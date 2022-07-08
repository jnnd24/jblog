package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogContoller {
	
	@RequestMapping(value="{id}")
	public String main(@PathVariable("id")String id, Model model) {
		System.out.println(" BlogCtrl > main");
		
		//id보내기
		model.addAttribute("id", id);
		
		return "blog/blog-main";
	}

}
