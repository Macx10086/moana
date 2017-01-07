package com.lab.io.rush.service;

import com.lab.io.rush.pojo.Loginer;

public interface LoginerService {

	void saveLoginer(Loginer login);

	String getPassByEmail(String email);

	boolean existsEmail(String email);

}
