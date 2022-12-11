package com.web.cart;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import com.web.user.User;
import com.web.util.DateTimeUtil;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Data
@Table(name = "shopping_session")
public class ShoppingSession implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer id;

	@OneToOne
	@JoinColumn(name = "user_id")
	@ToString.Exclude
	private User user;

	@Column(name = "extra_time")

	private Date exTime;

	@Column(name = "repair_time")
	private Date repTime;

	@Column(name = "cur_session")
	@Expose
	private String curSession;

	@Expose
	private Integer total;

	@OneToMany(mappedBy = "shopSession", fetch = FetchType.EAGER)
	@JsonIgnore
	@ToString.Exclude
	List<CartDetail> cd;

	public String toString() {
		return String.format("curID: %s, userEmail = %s", curSession, getUser().getEmail());
	}

	public void updateExTimeAsNow() {
		setExTime(DateTimeUtil.asCurrentDate());
	}

	public void increaseTotal() {
		this.total += 1;
	}

}
