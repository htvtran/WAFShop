package com.web.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.product.detail.Color;

public interface ColorDAO extends JpaRepository<Color,Integer>{

}