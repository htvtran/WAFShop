package com.web.supplier;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.web.product.detail.Supplier;

@RestController
@RequestMapping("/admin")
public class SupplierRestController {

	@Autowired
	SupplierRepository supRepo;

	@Autowired
	SupplierService supSurv;

	@RequestMapping("quanlynhacungcap")
	public ModelAndView getViewNCC(Model model, @RequestParam("p") Optional<Integer> p, HttpSession session) {
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		Page<Supplier> list = supRepo.findAllByCoop(pageable);
		model.addAttribute("ncc", list);
		Supplier sup = new Supplier();
		model.addAttribute("sup", sup);
		return new ModelAndView("admin/quanlynhacungcap");
	}

	@PostMapping("quanlynhacungcap/save")
	public ModelAndView saveNCC(@ModelAttribute("sup") Supplier sup) {
		supRepo.save(sup);
		return new ModelAndView("redirect:/admin/quanlynhacungcap");
	}

	@PostMapping("quanlynhacungcap/update")
	public ModelAndView updateNCC(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone_number = request.getParameter("phone_number");
		String email = request.getParameter("email");
		String note = request.getParameter("note");
		Supplier su = supRepo.findById2(id);
		su.setName(name);
		su.setAddress(address);
		su.setPhone_number(phone_number);
		su.setEmail(email);
		su.setNote(note);
		supRepo.save(su);
		return new ModelAndView("redirect:/admin/quanlynhacungcap");
	}

	@RequestMapping(value = "quanlynhacungcap/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteNCC(@PathVariable("id") Integer idSup) {
		System.out.println("---------------------------- +++++++++ " + idSup);
		Supplier supp = supRepo.findById2(idSup);
		supp.setCooperated(false);
		supRepo.save(supp);
		return new ModelAndView("redirect:/admin/quanlynhacungcap");
	}

	// TTTT
	@RequestMapping("quanlynhacungcap/click")
	public @ResponseBody String clickPageNumberNCC(Model model, @RequestParam("p") Integer p) {

		Pageable pageable = PageRequest.of(p, 5);
		Page<Supplier> list = supRepo.findAllByCoop(pageable);

		List<Supplier> sup = list.toList();
		String html = "";
		String tdText = "<td class=''><span class='text-dark'";

		for (Supplier i : sup) {

			html += "<tr>";
			html += "<td><i class='fab fa-angular fa-lg text-danger me-3'></i>" + i.getId() + "</td>";

			html += "<td class=''><span class='text-dark'>" + i.getName() + "</span></td> ";
			html += tdText + ">" + i.getAddress() + "</span></td>" + tdText + ">" + i.getPhone_number() + "</span></td>"
					+ tdText + ">" + i.getEmail() + "</span></td>";
			html += "<td class=''>" + "<div class='dropdown'>" + "<button type='button'"
					+ "class='btn p-0 dropdown-toggle hide-arrow'" + "data-bs-toggle='dropdown'>"
					+ "<i class='bx bx-dots-vertical-rounded'></i>" + "</button>" + "<div class='dropdown-menu'>" +

					"<a class='dropdown-item' data-bs-toggle='offcanvas'" + "data-bs-target='#offcanvasBackdrop2'"
					+ "aria-controls='offcanvasBackdrop2'> <i" + "class='bx bx-edit-alt me-2'></i>Sửa"
					+ "</a> <a class='dropdown-item'" + "th:href='@{'quanlynhacungcap/delete/'+${supplier.id}}'><i"
					+ "class='bx bx-trash me-2'></i> Delete</a>" + "</div></div></td>";
			html += "</tr>";
		}
		return html;
	}

	@RequestMapping("/quanlynhacungcap/ajaxData")
	public @ResponseBody String senDataNCC(HttpServletRequest request, @RequestParam("id") Integer id)
			throws JsonProcessingException {

		Optional<Supplier> os = supRepo.findById(id);
		Supplier sp = os.get();

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(sp);

		return json;

	}

	@RequestMapping("/quanlynhacungcap/search")
	public @ResponseBody String searchPost(Model model, HttpServletRequest request,
			@RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		
		String keyword = request.getParameter("txt");
		String html = "";
		String tdText = "<td class=''><span class='text-dark'";
		if (keyword != null) {
			Page<Supplier> listSearch = supRepo.findByKeyWord(keyword, pageable);
			List<Supplier> sup = listSearch.toList();
			
			for (Supplier i : sup) {

				html += "<tr>";
				html += "<td><i class='fab fa fa-lg text-danger me-3'></i>" + i.getId() + "</td>";

				html += "<td class=''><span class='text-dark'>" + i.getName() + "</span></td> ";
				html += tdText + ">" + i.getAddress() + "</span></td>" + tdText + ">" + i.getPhone_number()
						+ "</span></td>" + tdText + ">" + i.getEmail() + "</span></td>";
				html += "<td class=''>" + "<div class='dropdown'>" + "<button type='button'"
						+ "class='btn p-0 dropdown-toggle hide-arrow'" + "data-bs-toggle='dropdown'>"
						+ "<i class='bx bx-dots-vertical-rounded'></i>" + "</button>" + "<div class='dropdown-menu'>" +

						"<a class='dropdown-item' data-bs-toggle='offcanvas'" + "data-bs-target='#offcanvasBackdrop2'"
						+ "aria-controls='offcanvasBackdrop2'> <i" + "class='bx bx-edit-alt me-2'></i>Sửa"
						+ "</a> <a class='dropdown-item'" + "th:href='@{'quanlynhacungcap/delete/'+${supplier.id}}'><i"
						+ "class='bx bx-trash me-2'></i> Delete</a>" + "</div></div></td>";
				html += "</tr>";
			}

		} else {
			Page<Supplier> list = supRepo.findAll(pageable);	

			List<Supplier> sup = list.toList();

			for (Supplier i : sup) {

				html += "<tr>";
				html += "<td><i class='fab fa fa-lg text-danger me-3'></i>" + i.getId() + "</td>";

				html += "<td class='text-center'><span class='text-dark'>" + i.getName() + "</span></td> ";
				html += tdText + ">" + i.getAddress() + "</span></td>" + tdText + ">" + i.getPhone_number()
						+ "</span></td>" + tdText + ">" + i.getEmail() + "</span></td>";
				html += "<td class='text-center'>" + "<div class='dropdown'>" + "<button type='button'"
						+ "class='btn p-0 dropdown-toggle hide-arrow'" + "data-bs-toggle='dropdown'>"
						+ "<i class='bx bx-dots-vertical-rounded'></i>" + "</button>" + "<div class='dropdown-menu'>" +

						"<a class='dropdown-item' data-bs-toggle='offcanvas'" + "data-bs-target='#offcanvasBackdrop2'"
						+ "aria-controls='offcanvasBackdrop2'> <i" + "class='bx bx-edit-alt me-2'></i>Sửa"
						+ "</a> <a class='dropdown-item'" + "th:href='@{'quanlynhacungcap/delete/'+${supplier.id}}'><i"
						+ "class='bx bx-trash me-2'></i> Delete</a>" + "</div></div></td>";
				html += "</tr>";

			}
	
		}
		return html;
	}
}
