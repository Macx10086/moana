package com.lab.io.rush.service;

import java.util.Set;

public interface RedisService {

	public String insertKeyIntoSet(int id, String email);

	public void sendData();
	public void sendEmail(String toEmail,String movie);
	public void rangeSet(Set<byte[]> s,int id);

}
