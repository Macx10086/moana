package com.lab.io.rush.dao;

import com.lab.io.rush.pojo.Loginer;

public interface LoginerDao {

	public String getPassByEmail(String email);

	public void save(Loginer login);
	
	public Loginer getLoginerById(int id);
	
	public Loginer getLoginerByEmail(String email);

	public boolean existsEmail(String email);

}
