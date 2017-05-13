package com.lab.io.rush.dao;

import java.util.List;

import com.lab.io.rush.pojo.Movie;

public interface MovieDao {

	public void save(Movie movie);

	public List<Movie> getList();

	public Movie getMovie(int id);

	public List getMovieIdList();
	public void updateNum(int id, int num);

	public Long getMovieNum(String start, String end, String movieName, int grade);

	public List getPageList(int pageNo, int pageSize, String start, String end, String movieName, int i);

	public boolean deleteMovie(String id);

	public void updateMovieData(String rushTime,String id,String name, String director, String scenario,
			String protagonist, String type, String time, String intro,
			String assess, String grade, String poster, String num );

	public String getIdByName(String name);



}
