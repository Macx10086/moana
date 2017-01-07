
package com.lab.io.rush.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lab.io.rush.dao.MovieDao;
import com.lab.io.rush.dao.RedisDao;
import com.lab.io.rush.pojo.Movie;
import com.lab.io.rush.service.MovieService;

@Service("movieService")
public class MovieServiceImpl implements MovieService{
	@Resource
	private MovieDao movieDao;
	@Resource
	private RedisDao redisDao;

	@Override
	public void insertMovieData(Movie movie) {
		// TODO Auto-generated method stub
		movieDao.save(movie);
		
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
	

}
