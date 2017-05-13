package com.lab.io.rush.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lab.io.rush.dao.AdminDao;
import com.lab.io.rush.pojo.Admin;
import com.lab.io.rush.service.AdminService;
@Service("adminService")
public class AdminServiceImpl implements AdminService{
	@Resource
	private AdminDao adminDao;
	@Override
	public String getPasswordByEmail(String email) {
		// TODO Auto-generated method stub
		return adminDao.getPasswordByEmail(email);
	}
	@Override
	public boolean existsEmail(String email) {
		// TODO Auto-generated method stub
		return adminDao.existsEmail(email);
	}
	@Override
	public Admin getAdminByEmail(String email) {
		// TODO Auto-generated method stub
		return adminDao.getAdminByEmail(email);
	}
	@Override
	public boolean editInfo(String email, String password) {
		// TODO Auto-generated method stub
		return adminDao.editInfo(email,password);
	}

}
