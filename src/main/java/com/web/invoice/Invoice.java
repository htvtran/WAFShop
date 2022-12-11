package com.web.invoice;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import com.web.InvoiceDetail.InvoiceDetail;
import com.web.subcategory.SubCategory;
import com.web.user.User;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Data
@Table(name="invoice")
public class Invoice implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@Expose
	private User user;
	
	@Column(name="creation_time")
	@Expose
	private Date creationTime;
	
	@Column(name="status_inv")
	@Expose
	private Boolean status;
	
	@Column(name="delivery_address")
	@Expose
	private String deliveryAddress; 
	
	@Column(name="voucher_user_id")
	@Expose
	private Integer voucher_user_id;
	
	@Column(name="payment_method_id")
	@Expose
	private Integer paymentMethodId;
	
	@OneToMany(mappedBy = "invoice")
	@JsonIgnore
	private List<InvoiceDetail> listInvoiceDetail;
	
	public Double tongTien() {
		Double tong = 0.0;
		List<InvoiceDetail> list = this.listInvoiceDetail;
		for(int i=0;i<list.size();i++) {
			tong += list.get(i).getQuantity() * list.get(i).getProduct().getPrice();
		}
		
		
		return tong;
	}
}
