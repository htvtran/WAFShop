package com.web.subcategory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import com.web.category.Category;
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
@Table(name = "SUBCATEGORY")

public class SubCategory implements Serializable {

	@Id
	@Expose
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "subcategory_name")
	@Expose
	private String name;

	//
	private String positions;

	@ManyToOne
	@JoinColumn(name = "category_id")
	@Expose
	private Category category;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "subcat")
	@JsonIgnore
	@EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
	@ToString.Exclude // Khoonhg sử dụng trong toString()
	@Column(nullable = true)
	private List<Product> proList = new ArrayList<>();

	public int proListSize() {
		if (this.proList == null)
			return 0;
		return proList.size();
	}
}
