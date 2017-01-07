package com.lab.io.rush.dao.impl;

import java.util.List;

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

import com.lab.io.rush.dao.MovieDao;
import com.lab.io.rush.pojo.Movie;

@Repository("movieDao")
public class MovieDaoImpl implements MovieDao{
	
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(Movie movie) {
		// TODO Auto-generated method stub
		em.persist(movie);
		
	}

	@Transactional
	public List<Movie> getList() {
		// TODO Auto-generated method stub
		Query q=em.createNativeQuery("select * from MOVIE");
	    List l=q.getResultList();
	    return l;
	}

	@Transactional
	public Movie getMovie(int id) {
		// TODO Auto-generated method stub	
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<Movie> cq=cb.createQuery(Movie.class);
		Root<Movie> root=cq.from(Movie.class);
		Path<Integer> mid=root.get("id");
		Predicate p=cb.equal(mid, id);
		return em.createQuery(cq.where(p)).getSingleResult();
		
	}

	@Override
	public List getMovieIdList() {
		// TODO Auto-generated method stub
		Query q=em.createNativeQuery("select ID from MOVIE");
		return q.getResultList();
	}

	@Override
	public void updateNum(int id, int num) {
		// TODO Auto-generated method stub
		Query q=em.createNativeQuery("update MOVIE set NUM="+num+" where id="+id);
		q.executeUpdate();
		
	}


	
	

}
