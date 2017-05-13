package com.lab.io.rush.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lab.io.rush.dao.ManageMovieDao;
import com.lab.io.rush.pojo.ManageMovie;

@Repository("manageMovieDao")
public class ManageMovieDaoImpl implements ManageMovieDao
{
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(ManageMovie manageMovie) {
		// TODO Auto-generated method stub
		em.persist(manageMovie);
		
	}

	@Transactional
	public void deleteManageMovie(String id) {
		// TODO Auto-generated method stub
		String sql = "delete from MANAGEMOVIE where mid="+id;
		Query q = em.createNativeQuery(sql);
		q.executeUpdate();
	}
	
}
