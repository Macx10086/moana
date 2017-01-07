package com.lab.io.rush.pojo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-01-02T22:56:11.819+0800")
@StaticMetamodel(Loginer.class)
public class Loginer_ {
	public static volatile SingularAttribute<Loginer, Integer> id;
	public static volatile SingularAttribute<Loginer, String> email;
	public static volatile SingularAttribute<Loginer, String> password;
	public static volatile SingularAttribute<Loginer, Integer> id;
	public static volatile SetAttribute<Loginer, RushTicket> rushTicket;
}
