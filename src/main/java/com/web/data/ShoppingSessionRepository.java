package com.web.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.cart.ShoppingSession;
import com.web.user.User;


@Repository("ssrepo")

public interface ShoppingSessionRepository extends JpaRepository<ShoppingSession, Integer>{

	 public final static String GET_SUB_BY_CAT =
			 "select ss.user from ShoppingSession ss where ss.user.id = :sid";
	 
	 public final static String UPDATE_SESSION_BY_ID = 
			 "update ShoppingSession ss set ss.curSession =:sid, ss.total = ss.total+1 where ss.user.id =:uid";
			
 	@Procedure("removeCartItemAfterCheckOut")
			 void clearCart(Integer model);

	public final static String REMOVE_CART_ITEM ="";
	
	@Transactional
	 @Modifying
	  @Query(value = UPDATE_SESSION_BY_ID) 
	 void updateSession(@Param("sid") String sid, @Param("uid") Integer uid);
	 

	 
//	 Optional<ShoppingSession> findBySession(String session);
	 
	 Optional<ShoppingSession> findByCurSession(String curSession);
	 
	 Optional<ShoppingSession> findByUser(User user);
	 

	 
//	 Optional<String> findBySession(String session);
//	 
//	  @Query(value = GET_SUB_BY_CAT) 
//	 void selectBig();
}
