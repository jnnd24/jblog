package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	//회원가입 폼
	@RequestMapping(value="joinForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println(" UserCtrl > joinForm");
		
		return "user/joinForm";
	}
	
	//아이디 중복체크
	@RequestMapping(value="idcheck", method= {RequestMethod.GET, RequestMethod.POST})
	public String idcheck(@RequestParam("id")String id) {
		System.out.println(" UserCtrl > idcheck");
		
		String result = userService.idcheck(id);
		System.out.println(result);
		
		return result;
	}
	
	//회원가입
	@RequestMapping(value="join", method= {RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println(" UserCtrl > join");
		
		//계정 생성
		userService.join(userVo);
		
		return "user/joinSuccess";
	}
	
	
	//로그인 폼
	@RequestMapping(value="loginForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String loginForm(){
		System.out.println(" UserCtrl > loginForm");
		
		return "user/loginForm";
	}
	
	//로그인
	@RequestMapping(value="login",  method= {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println(" UserCtrl > login");
		
		UserVo authUser = userService.getUser(userVo);
		
		//세션에 담기
		session.setAttribute("authUser", authUser);
		
		System.out.println(authUser);
		
		if(authUser == null) { // 로그인 실패 시
			return "redirect:loginForm?result=fail";
		}else { 				//로그인 성공 시 메인으로 리다이렉트
			return "redirect:/";
		}
		
	}
	
	//로그아웃
	@RequestMapping(value="logout", method= {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		System.out.println(" UserCtrl > logout");
		
		session.removeAttribute("authUser");
		
		return "redirect:/";
	}
	
	

}
