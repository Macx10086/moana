package com.lab.io.rush.dao.impl;

import java.text.SimpleDateFormat;
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

	@Transactional
	public Long getMovieNum(String start, String end,
			String movieName, int grade) {
		String sql = "select count(1) from MOVIE where 1=1";
		if(grade!=0){
			sql += "  and GRADE> "+String.valueOf(grade-2)+" and GRADE<"+String.valueOf(grade);
		}
		if(!(start==null||start.equals(""))){
			sql += " and TIME>'"+start+"'";
		}
		if(!(end==null||end.equals(""))){
			sql +=" and TIME<'"+end+"'";
		}
		if(!(movieName==null||movieName.equals(""))){
			sql+=" and NAME like '%"+movieName+"%'";
		}
		return (Long) em.createNativeQuery(sql).getSingleResult();
	}

	

	@Transactional
	public List getPageList(int pageNo, int pageSize, String start, String end,
			String movieName, int grade) {
		String sql = "select * from MOVIE where 1=1";
		if(grade!=0){
			sql += "  and GRADE> "+String.valueOf(grade-2)+" and GRADE<"+String.valueOf(grade);
		}
		if(!(start==null||start.equals(""))){
			sql += " and TIME>'"+start+"'";
		}
		if(!(end==null||end.equals(""))){
			sql +=" and TIME<'"+end+"'";
		}
		if(!(movieName==null||movieName.equals(""))){
			sql+=" and NAME like '%"+movieName+"%'";
		}
		sql+= " limit "+pageNo+","+pageSize;
		Query q = em.createNativeQuery(sql,Movie.class);
		return q.getResultList();
	}

	@Transactional
	public boolean deleteMovie(String id) {
		// TODO Auto-generated method stub
		String sql = "delete from MOVIE where id="+Integer.valueOf(id);
		Query q = em.createNativeQuery(sql);
		return q.executeUpdate()>0?true:false;
	}

	@Transactional
	public void updateMovieData(String rushTime,String id,String name, String director, String scenario,
			String protagonist, String type, String time, String intro,
			String assess, String grade, String poster, String num) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Movie movie = getMovie(Integer.valueOf(id));
		if(rushTime==null||rushTime.equals("")){
			rushTime = movie.getRushTime();
		}
		if(name==null||name.equals("")){
			name = movie.getName();
		}if(director==null||director.equals("")){
			director = movie.getDirector();
		}
		if(scenario==null||scenario.equals("")){
			scenario = movie.getScenario();
		}
		if(protagonist==null||protagonist.equals("")){
			protagonist = movie.getProtagonist();
		}
		if(type==null||type.equals("")){
			type = movie.getType();
		}
		if(time==null||time.equals("")){
			time = sdf.format(movie.getTime());
		}
		if(intro==null||intro.equals("")){
			intro = movie.getIntro();
		}
		if(assess==null||assess.equals("")){
			assess = movie.getAssess();
		}
		if(grade==null||grade.equals("")){
			grade = movie.getGrade();
		}
		if(poster==null||poster.equals("")){
			poster = movie.getPoster();
		}
		if(num==null||num.equals("")){
			num = String.valueOf(movie.getNum());
		}
		
		String sql ="update MOVIE set Name = '"+name+"',"+
					"director='"+director+"',"+
					"scenario='"+scenario+"',"+
					"protagonist='"+protagonist+"',"+
					"type='"+type+"',"+
					"time='"+time+"',"+
					"intro='"+intro+"',"+
					"assess='"+assess+"',"+
					"rushTime='"+rushTime+"',"+
					"grade='"+grade+"',"+
					"poster='"+poster+"',"+
					"num='"+Integer.valueOf(num)+"' where id="+Integer.valueOf(id)+"";
		Query q = em.createNativeQuery(sql);
		q.executeUpdate();
	}

	@Override
	public String getIdByName(String name) {
		String sql = "select ID from MOVIE where NAME='"+name+"'";
		Query q= em.createNativeQuery(sql);
		return q.getSingleResult().toString();
		
	}





	
	

}
