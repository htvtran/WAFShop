package com.web.product;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update products set deleted=1 where id=:id", nativeQuery = true)
    void deleteID(@Param("id") Integer id);

    @Transactional
    @Query(value = "select * from products where deleted= :val", nativeQuery = true)
    Page<Product> toListProduct(@Param("val") Integer val, Pageable page);

    @Transactional
    @Query(value = "select * from products where deleted= :val and product_name like :search ", nativeQuery = true)
    Page<Product> toListProductBySearch(@Param("val") Integer val, @Param("search") String search, Pageable page);

    @Transactional
    @Query(value = "select sum((price * quantity)) from invoice vc join invoice_detail vcd on vc.id = vcd.invoice_id"+
    " join products pd on pd.id = vcd.product_id where year(creation_time) = :year" , nativeQuery = true)
    Float doanhThuTrongNam(@Param("year") Integer year);

    @Transactional
    @Query(value = "select sum((price * quantity)) from invoice vc join invoice_detail vcd on vc.id = vcd.invoice_id "+
    "join products pd on pd.id = vcd.product_id  where creation_time = :date", nativeQuery = true)
    Float doanhThuTrongNgay(@Param("date") String date);

}
