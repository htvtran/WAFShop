package com.web.product.detail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import com.web.product.Product;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Data
@Table(name = "SIZES")
public class Size  implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer id;
	
	@Column(name = "size_name")
	@Expose
	private String name;
	
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "size")
	@JsonIgnore
	 @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
		@ToString.Exclude // Khoonhg sử dụng trong toString()
	List<Product> proList = new ArrayList<>();
	

}
