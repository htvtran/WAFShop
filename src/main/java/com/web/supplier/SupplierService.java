package com.web.supplier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.product.detail.Supplier;

@Service
public class SupplierService {
	@Autowired
	private SupplierRepository supRepo;
	
	public List<Supplier> listAll(){
		return supRepo.findAll();
	}
	
	public void save(Supplier sup) {
		supRepo.save(sup);
	}
	
	
	public Supplier get(Integer id) {
		return supRepo.findById(id).get();
	}
	
	public void delete(Integer id) {
		supRepo.deleteById(id);
	}
	
	
//	public List<Supplier> findByKeyWord(String keyword){
//		return supRepo.findByKeyWordLike(keyword);
//	}
//	
	
	
//	public boolean isUniqueEmail(Integer id, String email) {
//		boolean isUniqueEmailViolated = false;
//		
//		Supplier supplierByEmail = supRepo.findBySupEmail(email);
//		boolean isCreatingNew = (id == null || id == 0);
//		
//		if(isCreatingNew) {
//			if(supplierByEmail != null) {
//				isUniqueEmailViolated = true;
//			}
//		}else {
//			if(supplierByEmail != null && supplierByEmail.getId() != id) {
//				isUniqueEmailViolated = true;
//			}
//		}
//		return isUniqueEmailViolated;
//	}
	
//	public Supplier findSupplierByEmail(String email) {
//		return supRepo.findBySupEmail(email);
//	}
	
}
