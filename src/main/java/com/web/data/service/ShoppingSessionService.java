package com.web.data.service;

import java.util.List;
import java.util.Optional;

import com.web.cart.CartDetail;
import com.web.cart.ShoppingSession;
import com.web.user.User;


public interface ShoppingSessionService {
	
	public boolean updateSession(String sessionID, User u);
	
	public boolean updateSession(String sessionID, Integer uid);
	
	public Optional<ShoppingSession> findByUserID(Integer id);
	
	public Optional<ShoppingSession> findBySession(String session);
	
	public List<ShoppingSession> findAll();
	
	public List<CartDetail> findCartDetailWithSession(String session);
	
	public boolean addProductToCart(Integer proId, String session);
	
	public void clearCart(Integer userId);

	public boolean reduceProductInCart(Integer proId, String session);
	
	public boolean updateProductInCart(Integer proId, Integer qty, String session) throws RuntimeException;
	
	
	

}
