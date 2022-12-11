package com.web.InvoiceDetail;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.web.invoice.Invoice;
import com.web.product.Product;
import com.web.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="invoice_detail")
public class InvoiceDetail implements Serializable{
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="invoice_id")
	@Expose
	private Invoice invoice;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	@Expose
	private Product product;
	
	@Column(name="quantity")
	@Expose
	private Integer quantity;
	
}
