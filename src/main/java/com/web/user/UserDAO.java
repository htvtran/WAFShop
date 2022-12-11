package com.web.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDAO extends JpaRepository<User, Integer>{
	
	public final static String GET_USER_BY_USERNAME = "SELECT * FROM heroku_844f026e121e684.USERS  WHERE FULLNAME like %:uName% AND role LIKE 'ROLE_USER'";
	
	
	 @Query(value = GET_USER_BY_USERNAME,  nativeQuery = true) //navtivequery???
	  List<User> findByUserName(@Param("uName")final String uName);
}
