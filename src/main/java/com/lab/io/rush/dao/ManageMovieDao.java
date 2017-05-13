package com.lab.io.rush.dao;

import com.lab.io.rush.pojo.ManageMovie;

public interface ManageMovieDao {
	
	void save(ManageMovie manageMovie);

	void deleteManageMovie(String id);

}
