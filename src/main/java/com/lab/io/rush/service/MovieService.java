
package com.lab.io.rush.service;

import java.text.ParseException;
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
		/**
		 * 
		
		 * @param grade 
		 * @param movieName 
		 * @param end 
		 * @param start 
		 * @Title: getMovieNum
		
		 * @Description: 获取电影数目
		
		 * @return
		
		 * @return: long
		 */
		public Long getMovieNum(String start, String end, String movieName, String grade);

		public List getPageList(int pageNo, int pageSize, String start, String end, String movieName, String grade)throws ParseException;
		/**
		 * 
		
		 * @Title: deleteMovie
		
		 * @Description: 根据id删除电影
		
		 * @param id
		 * @return
		
		 * @return: boolean
		 */
		public boolean deleteMovie(String id);
		/**
		 * 
		
		 * @Title: updateMovieData
		
		 * @Description: 更新电影信息
		
		 * @param name
		 * @param director
		 * @param scenario
		 * @param protagonist
		 * @param type
		 * @param time
		 * @param intro
		 * @param assess
		 * @param grade
		 * @param poster
		 * @param num
		 * @param num2 
		 * @param num2 
		
		 * @return: void
		 */
		public void updateMovieData(String rushTime,String id,String name, String director,
				String scenario, String protagonist, String type, String time,
				String intro, String assess, String grade, String poster,
				String num);
	
}