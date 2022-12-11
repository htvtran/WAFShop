//package com.web.supplier;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.web.product.detail.Supplier;
//
//@Controller
//public class SupplierController {
//	@Autowired
//	private SupplierService supService;
//	
//	@RequestMapping("/quanlynhacungcap")
//	public String viewSupplerPage(Model model) {
//		List<Supplier> listSuppliers = supService.listAll();
//		model.addAttribute("listSuppliers", listSuppliers);
//		Supplier sup = new Supplier();
//		model.addAttribute("sup",sup);
//		return "admin/quanlynhacungcap";
//	}
	
//	@RequestMapping("/quanlynhacungcap/edit/{id}")
//	public ModelAndView showEditSupplier(Model model, @PathVariable(name="id") int id) {
//		List<Supplier> listSuppliers = supService.listAll();
//		model.addAttribute("listSuppliers", listSuppliers);
//		ModelAndView mav = new ModelAndView("admin/edit-supplier");
//		Supplier sup = supService.get(id);
//		model.addAttribute("sup",sup);
//		
//		return mav;
//	}
	
//	@RequestMapping(value = "/quanlynhacungcap/save", method = RequestMethod.POST)
//	public String saveSupplier(@ModelAttribute("sup") Supplier sup) {
//		supService.save(sup);
//		return "redirect:/quanlynhacungcap";
//	}
//}
