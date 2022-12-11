package com.web.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.cart.Invoices;

@Repository(value = "ivrepo")
public interface InvoicesRepository extends JpaRepository<Invoices, Integer> {


} 