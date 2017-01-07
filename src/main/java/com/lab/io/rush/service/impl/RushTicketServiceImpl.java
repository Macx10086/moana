package com.lab.io.rush.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lab.io.rush.dao.LoginerDao;
import com.lab.io.rush.dao.MovieDao;
import com.lab.io.rush.dao.RushTicketDao;
import com.lab.io.rush.pojo.Loginer;
import com.lab.io.rush.pojo.Movie;
import com.lab.io.rush.pojo.RushTicket;
import com.lab.io.rush.service.RushTicketService;

@Service("rushService")
public class RushTicketServiceImpl implements RushTicketService{
	@Resource
	private RushTicketDao rushDao;
	@Resource
	private LoginerDao loginerDao;
	@Resource
	private MovieDao movieDao;
	
	@Override
	public void rushTicket(int id, String email) {
		// TODO Auto-generated method stub
		Movie movie=movieDao.getMovie(id);
		Loginer loginer=loginerDao.getLoginerByEmail(email);
		RushTicket rush=new RushTicket();
		rush.setLoginer(loginer);
		rush.setMovie(movie);
		rushDao.save(rush);
		
	}

}
