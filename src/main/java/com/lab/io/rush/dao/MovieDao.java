package com.lab.io.rush.dao;

import java.util.List;

import com.lab.io.rush.pojo.Movie;

public interface MovieDao {

	public void save(Movie movie);

	public List<Movie> getList();

	public Movie getMovie(int id);

	public List getMovieIdList();
	public void updateNum(int id, int num);


}
