package com.lab.io.rush.dao;

import org.springframework.data.jpa.repository.Query;

import com.lab.io.rush.pojo.TestPo;

public interface TestDao {
	public void save(TestPo test);
	@Query("update TEST set NAME='sss' where ID=1")
	public void update();
}
