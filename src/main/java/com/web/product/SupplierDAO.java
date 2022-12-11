package com.web.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.product.detail.Supplier;

public interface SupplierDAO  extends JpaRepository<Supplier,Integer>{
    
}
