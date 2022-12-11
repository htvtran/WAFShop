package com.web.cart;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.Expose;
import com.web.product.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cart_detail")
public class CartDetail implements Serializable{
	
	@Id
	@Expose
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	
	@ManyToOne
	@JoinColumn(name ="session_id")
	@Expose
	ShoppingSession shopSession;
	
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	@Expose
	Product product;
	
	
	@Column(name = "creation_time")
	Date creTime;
	
	@Column(name = "repair_time")
	Date repTime;
	
	@Expose
	Integer quantity;
	
	Boolean selected;
	
	

	public CartDetail(Integer id, Product product, ShoppingSession session,Integer quantity) {
		this.id = id;
		this.product = product;
		this.quantity = quantity;
		this.shopSession = session;
	}
	
	public void increase() {
		this.quantity += 1;
	}
	
	public void reduce() throws CartDetailQuantityIsZero {
		this.quantity -=1;
		if(this.quantity <=0) throw new CartDetailQuantityIsZero();
		
	}

	public void updateQuantity(int quantity) throws CartDetailQuantityIsZero {
		if(quantity <= 0)  throw new CartDetailQuantityIsZero();
		else setQuantity(quantity);
	}
	
	public class CartDetailQuantityIsZero extends Exception {
		static final String message = "gio hang 0";
		public CartDetailQuantityIsZero() {
			super(message);
			
		}
	}

	@Override
	public String toString() {
		return "CartDetail [id=" + id  +", product=" + product + ", creTime=" + creTime
				+ ", repTime=" + repTime + ", quantity=" + quantity + ", selected=" + selected + "]";
	}
	


	
}
