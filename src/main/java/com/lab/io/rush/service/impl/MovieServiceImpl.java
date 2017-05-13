
package com.lab.io.rush.service.impl;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lab.io.rush.dao.AdminDao;
import com.lab.io.rush.dao.ManageMovieDao;
import com.lab.io.rush.dao.MovieDao;
import com.lab.io.rush.dao.RedisDao;
import com.lab.io.rush.dao.RushTicketDao;
import com.lab.io.rush.pojo.ManageMovie;
import com.lab.io.rush.pojo.Movie;
import com.lab.io.rush.service.MovieService;

@Service("movieService")
public class MovieServiceImpl implements MovieService{
	@Resource
	private MovieDao movieDao;
	@Resource
	private RedisDao redisDao;
	@Resource
	private ManageMovieDao manageMovieDao;
	
	@Resource
	private AdminDao adminDao;
	
	@Resource
	private RushTicketDao rushTicketDao;
	

	@Override
	public void insertMovieData(Movie movie) {
		// TODO Auto-generated method stub
		movieDao.save(movie);
		ManageMovie manageMovie = new ManageMovie();
		manageMovie.setAdmin(adminDao.getAdminById(1));
		manageMovie.setMovie(movie);
		manageMovieDao.save(manageMovie);
		String id=movieDao.getIdByName(movie.getName());
		redisDao.addKey("movie"+id, movie.getNum()+"");
		
		
	}

	@Override
	public List<Movie> getMovieList() {
		// TODO Auto-generated method stub
		return movieDao.getList();
	}

	@Override
	public Movie getMovie(int id) {
		// TODO Auto-generated method stub
		return movieDao.getMovie(id);
	}

	@Override
	public String searchTicketNum(int id) {
		// TODO Auto-generated method stub
		
		String value=redisDao.getValue("movie"+id);
		if(value==null||value.equals("")){
			String num=movieDao.getMovie(id).getNum()+"";
			redisDao.addKey("movie"+id, num);
			return num;
		}
		return value;
		
	}

	@Override
	public Long getMovieNum(String start, String end,
			String movieName, String grade) {
		// TODO Auto-generated method stub
		return movieDao.getMovieNum(start,end,movieName,Integer.valueOf(grade)*2);
	}


	@Override
	public List getPageList(int pageNo, int pageSize, String start, String end,
			String movieName, String grade) throws ParseException {
		// TODO Auto-generated method stub
		pageNo = pageNo * pageSize;
		List<Movie> list = movieDao.getPageList(pageNo,pageSize,start,end,movieName,Integer.valueOf(grade)*2);
		
		return list;
	}

	@Override
	public boolean deleteMovie(String id) {
		// TODO Auto-generated method stub
		rushTicketDao.deleteMovie(id);
		manageMovieDao.deleteManageMovie(id);
		return movieDao.deleteMovie(id);
	}

	@Override
	public void updateMovieData(String rushTime,String id,String name, String director, String scenario,
			String protagonist, String type, String time, String intro,
			String assess, String grade, String poster, String num) {
		
			movieDao.updateMovieData(rushTime,id,name,director,scenario,protagonist,type,time,intro,assess,grade,poster,num);
	}
	

}
