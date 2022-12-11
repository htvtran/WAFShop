package com.web.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import com.web.cart.ShoppingSession;
import com.web.invoice.Invoice;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Data
@Table(name = "USERS")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer id;

	@Expose
	@Column(name = "fullname")
	@NotBlank(message = "Không để trống tên")
	private String name;

	@Expose
	@NotEmpty(message = "Không để trống email")
	@Email(message = "Email không đúng định dạng")
	private String email;

	@Expose
	private String role;

	@Expose
	@Length(min = 3, max = 30, message = "Độ dài password từ 3 đến 30")
	private String passwords;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "date_of_birth")
	@NotNull(message = "Bạn chưa nhập ngày sinh")
	private Date dob;

	@Expose
	private Boolean gender;

	@Column(name = "phone_number")
	private String phone;

	@Expose
	@NotNull(message = "Bạn chưa nhập địa chỉ")
	private String address;

	@Expose
	private Boolean actived;

	@Column(name = "reset_password_token")
	private String resetPasswordToken;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	@ToString.Exclude
	private ShoppingSession session;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Invoice> listInvoice;

	public String toString() {
		return String.format("Email:%s, name=%s, ID=%s", getEmail(), getName(), getId());
	}
}
