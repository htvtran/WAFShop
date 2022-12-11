package com.web.invoice;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.InvoiceDetail.InvoiceDetail;
import com.web.user.User;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Invoicep {

	private Integer id;
	private User user;
	private Date creationTime;
	private Boolean status;
	private String deliveryAddress; 
	private Integer voucher_user_id;
	private Integer paymentMethodId;
	private List<InvoiceDetail> listInvoiceDetail;
	private Double tongTien;
	
}
