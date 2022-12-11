package com.web.supplier;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.product.detail.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

	@Query("SELECT s FROM Supplier s where s.id = ?1")
	public Supplier findById2(Integer id);

	@Query("SELECT s FROM Supplier s where s.cooperated = 1")
	List<Supplier> findAll();

	@Query(value = "SELECT * FROM Suppliers  where supplier_name like %?1% or address like %?1%", nativeQuery = true)
	List<Supplier> findAllByKW(String keyword);

	@Query(value = "SELECT * FROM Suppliers  where supplier_name like %?1% or address like %?1% and cooperated = 1", nativeQuery = true)
	Page<Supplier> findByKeyWord(String keyword, Pageable pageable);

	@Query(value = "SELECT s FROM Supplier s where s.email like %?1%", nativeQuery = true)
	public Supplier findBySupEmail(String email);

	@Query("SELECT s FROM Supplier s where s.cooperated = ?1")
	public List<Supplier> findAll(Integer cooperated);

	@Query(value = "SELECT * FROM Suppliers WHERE cooperated = 1 ", nativeQuery = true)
	Page<Supplier> findAllByCoop(Pageable pageable);

	@Query("UPDATE Supplier s SET s.cooperated = 0 WHERE s.id = ?1")
	public Supplier updateToOutList(Integer id);

}
