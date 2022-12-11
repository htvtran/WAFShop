package com.web.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import com.web.InvoiceDetail.InvoiceDetail;
import com.web.cart.CartDetail;
import com.web.product.detail.Color;
import com.web.product.detail.Size;
import com.web.product.detail.Supplier;
import com.web.subcategory.SubCategory;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Data
@Table(name = "PRODUCTS")
public class Product implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer id;
	
	@Column(name = "product_name")
	@Expose
	@javax.validation.constraints.Size(min = 5,max = 100,message = "toi thieu 5 va max 10")
	@NotEmpty(message = "{NotEmpty.Product.name}")
	private String name;

	@Expose
	private Integer discount;
	
	@Expose
	private Double price;
	
	@Expose
	private String image;
	
	@Expose
	private String description;
	
	@Expose
	private Integer amount;
	
	
	@Expose
	Integer deleted;
	
	

	@ManyToOne
	@JoinColumn(name = "subcategory_id")
	@Expose
	SubCategory subcat;
	
	@ManyToOne
	@JoinColumn(name = "size_id")
	@Expose
	Size size;
	
	
	@ManyToOne
	@JoinColumn(name = "color_id")
	@Expose
	Color color;
	
	
	@ManyToOne
	@JoinColumn(name = "supplier_id")
	@Expose
	Supplier supplier;
	
	
	@OneToMany(mappedBy = "product")
	@JsonIgnore
	List<CartDetail> cd;
	
	@OneToMany(mappedBy = "product")
	@JsonIgnore
	List<InvoiceDetail> listInvoiceDetail;


	public Integer getCatID() {
		return this.getSubcat().getCategory().getId();
	}

	public String getCatName() {
		return this.getSubcat().getCategory().getCategoryName();
	}

	public String toString(){
		return String.format("pid = %d | name = %s", getId(), getName() );
	}

	public Set<Color> getColors() {
		SubCategory sub =  getSubcat();
		Set<Color> list = new HashSet<>();

		for(Product p : sub.getProList()) {
			list.add(p.getColor());
		}
		return list;
	}
	
	public Set<Size> getSizes() {
		SubCategory sub =  getSubcat();
		Set<Size> list = new HashSet<>();

		for(Product p : sub.getProList()) {
			list.add(p.getSize());
		}

		return list;
	}
}
