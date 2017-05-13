package com.lab.io.rush.service;

import com.lab.io.rush.pojo.Admin;

public interface AdminService {
	/**
	 * 
	
	 * @Title: getPasswordByEmail
	
	 * @Description: 根据账号获取密码
	
	 * @param email
	 * @return
	
	 * @return: String
	 */
	String getPasswordByEmail(String email);
	/**
	 * 
	
	 * @Title: existsEmail
	
	 * @Description: 查看邮箱是否存在
	
	 * @param email
	 * @return
	
	 * @return: boolean
	 */
	boolean existsEmail(String email);
	/**
	 * 
	
	 * @Title: getAdminByEmail
	
	 * @Description: 根据email获取管理员信息
	
	 * @param email
	 * @return
	
	 * @return: Admin
	 */
	Admin getAdminByEmail(String email);
	/**
	 * 
	
	 * @Title: editInfo
	
	 * @Description: 修改管理员信息
	
	 * @param email
	 * @param password
	 * @return
	
	 * @return: boolean
	 */
	boolean editInfo(String email, String password);

}
