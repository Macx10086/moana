package com.lab.io.rush.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lab.io.rush.pojo.Movie;
import com.lab.io.rush.service.MovieService;
import com.lab.io.rush.service.RedisService;


/**
 * @author Macx
 * @Description:作为控制层，调用不同的controller方法进行处理
 */
@RequestMapping("movieData")
@Controller
public class MovieDataController {
	
	@Resource
	private MovieService movieService;
	
	
	
	/**
	
	 * @Title: movieDataUpload
	
	 * @Description: 将电影数据持久化到数据库
	
	 * @param movie
	 * @return
	
	 * @return: String
	 * @throws IOException 
	
	 */
	@RequestMapping("movieDataUpload")
	public void  movieDataUpload(Movie movie,HttpServletResponse response) throws IOException{
		try{
			movieService.insertMovieData(movie);
		}catch(Exception e)
		{
			
			e.printStackTrace();
			response.sendRedirect("http://139.199.164.23/moana/static/error.html");
		}
		response.sendRedirect("http://139.199.164.23/moana/static/index.html");
		
	}
	
	/**
	
	 * @Title: getMovieList
	
	 * @Description: 获取电影列表显示
	
	 * @return
	
	 * @return: List
	
	 */
	@ResponseBody
	@RequestMapping(value="getMovieList")
	
	public List getMovieList(){
		List<Movie> al=new ArrayList<Movie>();
		try{
			al=movieService.getMovieList();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return al;
 	}
	
	/**
	
	 * @Title: forwardToShow
	
	 * @Description: 调到电影页面
	
	 * @param id
	 * @return
	
	 * @return: ModelAndView
	
	 */
	@RequestMapping("forwardToShow")
	public ModelAndView forwardToShow(int id){
		return new ModelAndView("movieShow",null);
	}
	
	/**
	
	 * @Title: showMovie
	
	 * @Description: 获取某部影片
	
	 * @param id
	 * @return
	
	 * @return: Map<String,Movie>
	
	 */
	@RequestMapping(value="showMovie.do")
	
	public @ResponseBody Movie showMovie(int id){
		Movie m=null;
		try{
			m=movieService.getMovie(id);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		return m;
	}
	
/**

 * @Title: searchTicketNum

 * @Description:查询剩余票数

 * @param id
 * @return

 * @return: String

 */
@RequestMapping(value="searchTicketNum.do")
	
	public @ResponseBody Integer searchTicketNum(int id){
	Integer num=null;
		try{
			num=Integer.valueOf(movieService.searchTicketNum(id));
//			num=100;
		}catch(Exception e){
			return null;
		}
		
		return num;
	}
	
	
	
}
