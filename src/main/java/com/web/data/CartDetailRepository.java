package com.web.data;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.cart.CartDetail;
import com.web.product.Product;

@Repository("cartdrepo")
public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {

	public final static String GET_PRODUCT_IN_CART = "SELECT cd from CartDetail cd "
			+ "join ShoppingSession ss on ss.id = cd.shopSession.id "
			+ "join Product p on cd.product.id = p.id where ss.id = :sid and p.id = :proid";
	
	//DELETE LATER
	public final static String GET_PRODUCT_BY_ID = "SELECT p from Product p where p.id = :pid";
	
	
//	public final static String GET_CART_BY_ID= "SELECT cd from CartDetail cd  where cd.product.id = :pid";
	
	public Optional<CartDetail> findByShopSession(String session);
	 
	 
	 
//	 public Optional<Product> findBy
	
	 @Query(value = GET_PRODUCT_IN_CART)
	 public Optional <CartDetail> findProductInCart(@Param("sid") Integer sid, @Param("proid") Integer proid );
	 
	 @Query(value =GET_PRODUCT_BY_ID)
	 public Optional<Product> getProduct(@Param("pid")Integer pid);
	 

}
