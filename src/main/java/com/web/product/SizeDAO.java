package com.web.product;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.product.detail.Size;

public interface SizeDAO extends JpaRepository<Size,Integer>{

    @Transactional
    @Query(value = "select sum((price * quantity))"+
    "from invoice iv join invoice_detail ivd  on iv.id = ivd.invoice_id "+
    "join products pd on ivd.product_id = pd.id where year(creation_time) = :curYear "+
    "and month(creation_time) = :month",nativeQuery = true)
    Float tongTienTungThangTheoNam(@Param("curYear") Integer year,@Param("month") Integer month);
}