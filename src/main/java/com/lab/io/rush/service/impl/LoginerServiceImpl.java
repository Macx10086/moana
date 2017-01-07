package com.lab.io.rush.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lab.io.rush.dao.LoginerDao;
import com.lab.io.rush.pojo.Loginer;
import com.lab.io.rush.service.LoginerService;

@Service("loginerService")
public class LoginerServiceImpl implements LoginerService{
	@Resource
	private LoginerDao loginerDao;

	@Override
	public void saveLoginer(Loginer login) {
		// TODO Auto-generated method stub
		loginerDao.save(login);
	}

	@Override
	public String getPassByEmail(String email) {
		// TODO Auto-generated method stub
		return loginerDao.getPassByEmail(email);
	}
}
