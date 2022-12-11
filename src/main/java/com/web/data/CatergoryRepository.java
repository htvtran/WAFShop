package com.web.data;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.web.category.Category;


@EnableJpaRepositories
@Repository("catrepo")
public interface CatergoryRepository extends JpaRepository<Category, Integer>, Serializable {
	
	

}
