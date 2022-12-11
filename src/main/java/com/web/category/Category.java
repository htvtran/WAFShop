package com.web.category;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
import com.web.subcategory.SubCategory;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Data
@Table(name = "CATEGORY")
public class Category implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer id;

	@Column(name = "category_name")
	@Expose
	private String categoryName;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
	@JsonIgnore
	@EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
	@ToString.Exclude // Khoonhg sử dụng trong toString()
	@Column(nullable = true)
	List<SubCategory> subCategory = new ArrayList<>();

	public int getTotalProductOfSub(Integer id) {
		List<SubCategory> subList = this.getSubCategory();
		if (subList == null)
			return 0;

		for (SubCategory sub : subList) {
			if (sub.getId() == id) {
				return sub.getProList() == null ? 0 : sub.getProList().size();
			}
		}
		return -1;
	}

	public boolean isProductListEmpty() {
		List<SubCategory> subList = this.getSubCategory();
		for (SubCategory sub : subList) {
			if (!sub.getProList().isEmpty())
				return false;
		}
		return true;
	}

}
