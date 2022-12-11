package com.web.cart;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;
import com.web.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="invoice")
public class Invoices {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
	@JoinColumn(name = "user_id")
	@Expose
    User user;

    @Temporal(TemporalType.DATE)
    @Column(name="creation_time")
    private Date creTime;

    @Column(name="status_inv")
    private Boolean status;

    @Column(name="delivery_address")
    private String address;

    @Column(name = "voucher_user_id")
    private Integer voucher_id;

    @Column(name= "payment_method_id")
    private Integer payId;

    public Invoices(User u) {
        this.user = u;
    this.status = null;
    this.address = null;
    this.voucher_id = null;
    this.payId = null;
    }
    @PrePersist
	public void preCreateDate() {
		creTime = new Date();
	}
    
}
