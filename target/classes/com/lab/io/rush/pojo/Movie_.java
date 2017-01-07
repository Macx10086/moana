package com.lab.io.rush.pojo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-01-02T22:56:11.997+0800")
@StaticMetamodel(Movie.class)
public class Movie_ {
	public static volatile SingularAttribute<Movie, Integer> id;
	public static volatile SingularAttribute<Movie, String> name;
	public static volatile SingularAttribute<Movie, String> director;
	public static volatile SingularAttribute<Movie, String> scenario;
	public static volatile SingularAttribute<Movie, String> protagonist;
	public static volatile SingularAttribute<Movie, String> type;
	public static volatile SingularAttribute<Movie, String> time;
	public static volatile SingularAttribute<Movie, String> intro;
	public static volatile SingularAttribute<Movie, String> assess;
	public static volatile SingularAttribute<Movie, String> grade;
	public static volatile SingularAttribute<Movie, String> poster;
	public static volatile SingularAttribute<Movie, Integer> num;
	public static volatile SingularAttribute<Movie, Integer> id;
	public static volatile SetAttribute<Movie, RushTicket> rushticket;
}
