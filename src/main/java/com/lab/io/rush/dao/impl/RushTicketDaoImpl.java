package com.lab.io.rush.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lab.io.rush.dao.RushTicketDao;
import com.lab.io.rush.pojo.RushTicket;

@Repository("rushTicketDao")
public class RushTicketDaoImpl implements RushTicketDao{
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void save(RushTicket rushTicket){
		em.persist(rushTicket);
		
	}
	

}
