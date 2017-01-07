
package com.lab.io.rush.service;

import java.util.List;

import com.lab.io.rush.pojo.Movie;

public interface MovieService{
	
		/**
		
		 * @Title: insertMovieData
		
		 * @Description: 将电影信息插入数据库
		
		 * @param movie
		
		 * @return: void
		
		 */
		public void insertMovieData(Movie movie);

		/**
		
		 * @Title: getMovieList
		
		 * @Description: 获取电影信息
		
		 * @return
		
		 * @return: ArrayList<Movie>
		
		 */
		public List<Movie> getMovieList();

		/**
		
		 * @Title: getMovie
		
		 * @Description: 获取某部影片
		
		 * @param id
		 * @return
		
		 * @return: Movie
		
		 */
		public Movie getMovie(int id);

		public String searchTicketNum(int id);
	
}