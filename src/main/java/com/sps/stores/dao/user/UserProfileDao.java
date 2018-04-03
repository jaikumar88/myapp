package com.sps.stores.dao;

import java.util.List;

import com.sps.stores.model.UserProfile;


public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
