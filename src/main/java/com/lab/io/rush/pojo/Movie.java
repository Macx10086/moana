package com.lab.io.rush.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="movie")
public class Movie implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id; //电影id
	private String name;//电影名称
	private String director;//电影导演
	private String scenario; //电影编剧
	private String protagonist;//电影主演
	private String type;//电影类型
	private String time;//电影上演日期
	@Column(length=5000)
	private String intro;//电影简介
	@Column(length=5000)
	private String assess;//电影评价
	private String grade;//电影评分
	private String poster;//电影海报
	private int num;//电影票数目
	private String rushTime;
	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getScenario() {
		return scenario;
	}
	public void setScenario(String scenario) {
		this.scenario = scenario;
	}
	
	
	public String getRushTime() {
		return rushTime;
	}
	public void setRushTime(String rushTime) {
		this.rushTime = rushTime;
	}
	public String getProtagonist() {
		return protagonist;
	}
	public void setProtagonist(String protagonist) {
		this.protagonist = protagonist;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getAssess() {
		return assess;
	}
	public void setAssess(String assess) {
		this.assess = assess;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
