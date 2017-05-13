package com.lab.io.rush.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lab.io.rush.pojo.Movie;
import com.lab.io.rush.service.MovieService;


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
	@ResponseBody
	public Map<String,Object>  movieDataUpload(String rushTime,String name,String director,String scenario,String protagonist,String type,String time,String intro,String assess,String grade,String poster,String num) throws IOException{
		Map data = new HashMap<String,Object>();
		try{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Movie movie = new Movie();
			movie.setAssess(assess);
			movie.setDirector(director);
			movie.setGrade(grade);
			movie.setIntro(intro);
			movie.setName(name);
			movie.setNum(Integer.valueOf(num));
			movie.setPoster(poster);
			movie.setProtagonist(protagonist);
			movie.setScenario(scenario);
			movie.setTime(time);
			movie.setType(type);
			movie.setRushTime(rushTime);
			
			movieService.insertMovieData(movie);
			data.put("status", 1);
			data.put("message", "添加电影信息成功");
		}catch(Exception e)
		{
			data.put("status", 0);
			data.put("message", "添加电影信息失败");
			e.printStackTrace();
		}
		return data;
		
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
	
/**
 * 

 * @Title: getMovieNum

 * @Description: 获取电影数目

 * @return

 * @return: Map<String,Object>
 */
@RequestMapping("getMovieNum.do")
@ResponseBody
	public Map<String,Object> getMovieNum(String start, String end,
			String movieName, String grade){
	Map data = new HashMap<String,Object>();
	try{
		data.put("num", movieService.getMovieNum(start,end,movieName,grade));
		data.put("status", 1);
		data.put("message", "查询失败");
	}catch(Exception e){
		data.put("status", 0);
		data.put("message", "查询失败");
		e.printStackTrace();
	}
	return data;
}
/**
 * 

 * @Title: getPageList

 * @Description: 获取分页数据

 * @return

 * @return: Map<String,Object>
 */
@RequestMapping("getPageList.do")
@ResponseBody
public Map<String,Object> getPageList(String pageNo,String pageSize,String start,String end,String movieName,String grade){
	Map<String, Object> data = new HashMap<String, Object>();
	if(pageNo==null||pageNo.equals("")) pageNo = "1";
	if(pageSize==null||pageSize.equals("")) pageSize = "5";
	try{
		data.put("status", 1);
		data.put("message", "查询成功");
		data.put("list", movieService.getPageList(Integer.valueOf(pageNo),Integer.valueOf(pageSize),start,end,movieName,grade));
	}catch(Exception e){
		data.put("status", 0);
		data.put("message", "查询失败");
		e.printStackTrace();
	}
	return data;
}

@RequestMapping("deleteMovie.do")
@ResponseBody
public Map<String,Object> deleteMovie(String id){
	Map<String,Object> data = new HashMap<String, Object>();
	try{
		if(movieService.deleteMovie(id)){
			data.put("status", 1);
			data.put("message", "删除成功");
		}
	}catch(Exception e){
		e.printStackTrace();
		data.put("status", 0);
		data.put("message", "删除失败");
	}
	return data;
}
/**
 * 

 * @Title: updateMovie

 * @Description: 更新电影信息

 * @return

 * @return: Map<String,Object>
 */
@RequestMapping("updateMovie.do")
@ResponseBody
public Map<String,Object> updateMovie(String rushTime,String id,String name,String director,String scenario,String protagonist,String type,String time,String intro,String assess,String grade,String poster,String num) throws IOException{
	Map data = new HashMap<String,Object>();
	try{
		
		movieService.updateMovieData(rushTime,id,name,director,scenario,protagonist,type,time,intro,assess,grade,poster,num);
		data.put("status", 1);
		data.put("message", "修改电影信息成功");
	}catch(Exception e)
	{
		data.put("status", 0);
		data.put("message", "修改电影信息失败");
		e.printStackTrace();
	}
	return data;
	}
}
