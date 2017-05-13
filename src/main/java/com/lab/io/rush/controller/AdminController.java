package com.lab.io.rush.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lab.io.rush.pojo.Admin;
import com.lab.io.rush.service.AdminService;

@RequestMapping("admin")
@Controller
/**
 * 
 * @author Macx
 *管理员拦截器
 */
public class AdminController {
	@Resource
	private AdminService adminService;
	/**
	 * 
	
	 * @Title: adminLogin
	
	 * @Description: 判断管理员登录
	
	 * @param email
	 * @param password
	 * @param request
	 * @param response
	 * @return
	
	 * @return: String
	 */
	@RequestMapping("login")
	@ResponseBody
	public Map adminLogin(String email,String password,HttpServletRequest request,HttpServletResponse response){
		Cookie[] cks = request.getCookies();
		Map map = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		String sss = (String) session.getAttribute("adminName");
		if((session.getAttribute("adminName")==null||session.getAttribute("adminName").equals(""))||!session.getAttribute("adminName").equals(email)){
			if(adminService.existsEmail(email)){
				String pass = adminService.getPasswordByEmail(email);
				if(password.equals(pass)){
					map.put("status", 1);
					map.put("message", "登陆成功");
					map.put("user", email);
					session.setAttribute("adminName", email);
					
				}else{
					map.put("status", 0);
					map.put("message", "请检查您的账号和密码是否匹配");
				}
			}else{
				map.put("status", 0);
				map.put("message", "请检查您的账号和密码是否匹配");
			}
			
		}else{
			map.put("status", 1);
			map.put("message", "您已登录");
			map.put("user", email);
		}
		return map;
	}
	/**
	 * 
	
	 * @Title: getInfo
	
	 * @Description: 根据email查找信息
	
	 * @param email
	 * @return
	
	 * @return: Map<String,Object>
	 */
	@RequestMapping("getInfo")
	@ResponseBody
	public Map<String,Object> getInfo(String email){
		Map data = new HashMap<String, Object>();
		try{
			Admin admin = adminService.getAdminByEmail(email);
			data.put("status", 1);
			data.put("message", "查询成功");
			data.put("admin", admin);
		}catch(Exception e){
			data.put("status", 0);
			data.put("message", "查询失败");
			e.printStackTrace();
			
		}
		return data;
	}
	/***
	 * 
	
	 * @Title: editInfo
	
	 * @Description: 修改管理员信息
	
	 * @param email
	 * @param password
	 * @return
	
	 * @return: Map<String,Object>
	 */
	@RequestMapping("editInfo")
	@ResponseBody
	public Map<String,Object> editInfo(String email,String password){
		Map data = new HashMap<String,Object>();
		try{
			if(adminService.editInfo(email,password)){
				data.put("status", 1);
				data.put("messag", "修改信息成功");
			}
			
		}catch(Exception e){
			data.put("status", 0);
			data.put("message", "修改信息失败！");
			e.printStackTrace();
		}
		return data;
	}
	/**
	 * 
	
	 * @Title: loginOut
	
	 * @Description: 登出
	
	 * @param request
	 * @return
	
	 * @return: Map<String,Object>
	 */
	@RequestMapping("loginOut")
	@ResponseBody
	public Map<String,Object> loginOut(HttpServletRequest request){
		Map data = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		session.removeAttribute("adminName");
		data.put("status", 1);
		data.put("message", "注销成功");
		return data;
	}
}
