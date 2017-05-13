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

import com.lab.io.rush.dao.AdminDao;
import com.lab.io.rush.pojo.Admin;
import com.lab.io.rush.pojo.Loginer;
@Repository("adminDao")
public class AdminDaoImpl implements AdminDao {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public String getPasswordByEmail(String email) {
		// TODO Auto-generated method stub
		String sql = "select PASSWORD from ADMIN where email='"+email+"'";
		Query q = em.createNativeQuery(sql);
		return q.getSingleResult().toString();
	}

	@Transactional
	public boolean existsEmail(String email) {
		// TODO Auto-generated method stub
		String l="select EMAIL from ADMIN";
		Query q=em.createNativeQuery(l);
		
		List<String> re= q.getResultList();
		if(re==null||!re.contains(email)) return false;
		return true;
	}

	@Transactional
	public Admin getAdminByEmail(String email) {
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<Admin> cq=cb.createQuery(Admin.class);
		Root<Admin> root=cq.from(Admin.class);
		Path<String> e=root.get("email");
		Predicate p=cb.equal(e,email);
		return em.createQuery(cq.where(p)).getSingleResult();
	}

	@Transactional
	public boolean editInfo(String email, String password) {
		// TODO Auto-generated method stub
		String sql = "update ADMIN  set PASSWORD = '" + password+"' where EMAIL='"+email+"'";
		Query q = em.createNativeQuery(sql);
		return q.executeUpdate()!=0;
	}

	@Transactional
	public Admin getAdminById(int id) {
		String sql = "select * from ADMIN where id="+id;
		Query q = em.createNativeQuery(sql,Admin.class);
		return (Admin) q.getSingleResult();
	}
}
