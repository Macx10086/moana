package com.lab.io.rush.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lab.io.rush.service.RedisService;
import com.lab.io.rush.service.RushTicketService;

/**
 * @author Macx
 *用来抢票的控制层
 */
@RequestMapping("rush")
@Controller
public class RushController {
	@Resource
	private RushTicketService rushService;
	@Resource
	private RedisService redisService;
	
	/**
	
	 * @Title: rushTicket
	
	 * @Description: 抢票
	
	 * @param id
	 * @param request
	 * @return
	
	 * @return: String
	
	 */
	@RequestMapping(value="rushTicket",method=RequestMethod.POST)
	@ResponseBody
	public String rushTicket(String id,String email,HttpServletRequest request){
		HttpSession session=request.getSession();
//		email=(String) session.getAttribute("username");
		String status="抢票失败";
		try{
			status=redisService.insertKeyIntoSet(Integer.valueOf(id),email);
		}catch(Exception e){
			return "error";
		}
		return status;
		
	}
	

}
