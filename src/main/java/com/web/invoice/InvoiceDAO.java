package com.web.invoice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceDAO extends JpaRepository<Invoice,Integer>{

}