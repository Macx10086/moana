package com.lab.io.rush.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lab.io.rush.dao.TestDao;
import com.lab.io.rush.pojo.TestPo;

@Repository("testDao")
public class TestDaoImpl implements TestDao{

	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void save(TestPo test) {
		// TODO Auto-generated method stub
		em.persist(test);
		
	}

	@Transactional
	public void update() {
		// TODO Auto-generated method stub
        Query q= em.createNativeQuery("update TEST set NAME='sss' where ID=1");
        q.executeUpdate();
        
		
	}

}
