package com.lab.io.rush.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lab.io.rush.pojo.Loginer;
import com.lab.io.rush.service.LoginerService;
import com.lab.io.rush.service.MovieService;


/**
 * @author Macx
 * 登录界面点击登录，写入session数据
 *
 */
/**
 * @author Admin
 *
 */
@RequestMapping("auth")
@Controller
public class LoginController {
	@Resource
	private LoginerService loginerService;
	
	
	/**
	
	 * @Title: login
	
	 * @Description: 对登录进行判断
	
	 * @param email
	 * @param password
	 * @return
	
	 * @return: String
	
	 */
	@RequestMapping("login")
	@ResponseBody
	public String login(String email,String password,HttpServletRequest request,HttpServletResponse response){
		if(!loginerService.existsEmail(email)){
			return "NoExists";
		}
		String pass=loginerService.getPassByEmail(email);
		HttpSession session=request.getSession();
		if(pass.equals(password)){
			session.setAttribute("username", email);
			Cookie cookie=new Cookie("username", email);
			response.addCookie(cookie);
			return email; 
		}
		return "false"; 
		
	}
	/**
	
	 * @Title: register
	
	 * @Description: 进行注册
	
	 * @param email
	 * @param password
	 * @return
	
	 * @return: String
	
	 */
	@RequestMapping("register")
	@ResponseBody
	public String register(String email,String password){
		Loginer login=new Loginer();
		login.setEmail(email);
		login.setPassword(password);
		try{
			loginerService.saveLoginer(login);
		}catch(Exception e){
			e.printStackTrace();
			return "false";
		}
		return "true";
	}
	
	@RequestMapping("loginOut")
	@ResponseBody
	public String loginOut(HttpServletRequest request,HttpServletResponse response){
		HttpSession session=request.getSession();
		String email = (String) session.getAttribute("username");
		session.removeAttribute("username");
		Cookie c=new Cookie("username", email);
		c.setMaxAge(0);
		response.addCookie(c);
		return "true";
	}
}

	
