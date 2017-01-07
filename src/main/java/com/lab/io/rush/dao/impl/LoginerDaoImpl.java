package com.lab.io.rush.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lab.io.rush.dao.LoginerDao;
import com.lab.io.rush.pojo.Loginer;
import com.lab.io.rush.pojo.Movie;

@Repository("loginerDao")
public class LoginerDaoImpl implements LoginerDao{
	@PersistenceContext
	private EntityManager em;
	

	@Transactional
	public String getPassByEmail(String email) {
		// TODO Auto-generated method stub
		String l="select PASSWORD from LOGINER where EMAIL='"+email+"'";
		Query q=em.createNativeQuery(l);
		
		return q.getSingleResult().toString();
	}

	@Transactional
	public void save(Loginer login) {
		// TODO Auto-generated method stub
		em.persist(login);
		
	}
	@Transactional 
	public Loginer getLoginerById(int id){
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<Loginer> cq=cb.createQuery(Loginer.class);
		Root<Loginer> root=cq.from(Loginer.class);
		Path<Integer> mid=root.get("id");
		Predicate p=cb.equal(mid, id);
		return em.createQuery(cq.where(p)).getSingleResult();
	}
	
	@Transactional
	public Loginer getLoginerByEmail(String email){
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<Loginer> cq=cb.createQuery(Loginer.class);
		Root<Loginer> root=cq.from(Loginer.class);
		Path<String> e=root.get("email");
		Predicate p=cb.equal(e,email);
		return em.createQuery(cq.where(p)).getSingleResult();
	}
}
