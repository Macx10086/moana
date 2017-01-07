package com.lab.io.rush.pojo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="rushticket")
public class RushTicket implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private int id;
	@Column(columnDefinition="INT default 0")
	private int num;
	@ManyToOne
	@JoinColumn(name="mid",unique=true)
	private Movie movie;
	@ManyToOne
	@JoinColumn(name="lid",unique=true)
	private Loginer loginer;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}

	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	public Loginer getLoginer() {
		return loginer;
	}
	public void setLoginer(Loginer loginer) {
		this.loginer = loginer;
	}
	

}
