package com.web.data;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.user.User;

@Repository("urepo")
public interface UserRepository extends JpaRepository<User, Integer> {
	
    Optional<User> findByEmail(String email);
	
	@Query("SELECT u FROM User u where u.email = ?1")
	public User findByEmail1(String email);
	
	
	public User findByResetPasswordToken(String token);
	
	@Query(value = "SELECT * FROM users WHERE email LIKE %:email%", nativeQuery = true)
	Page<User> findByEmail(@Param("email") String email, Pageable pageable);
}
