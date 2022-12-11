package com.web.admin;

import java.io.File;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.web.InvoiceDetail.InvoiceDetail;
import com.web.base.BaseRestController;
import com.web.category.Category;
import com.web.category.CategoryDAO;
import com.web.data.SubCatergoryRepository;
import com.web.data.UserRepository;
import com.web.files.FilesStorageService;
import com.web.invoice.Invoice;
import com.web.invoice.InvoiceDAO;
import com.web.invoice.Invoicep;
import com.web.product.ColorDAO;
import com.web.product.Product;
import com.web.product.ProductDAO;
import com.web.product.SizeDAO;
import com.web.product.SupplierDAO;
import com.web.subcategory.SubCategory;
import com.web.subcategory.SubCategoryDAO;
import com.web.user.User;
import com.web.user.UserDAO;

@RestController
@RequestMapping("/admin")
public class AdminController extends BaseRestController<AdminController> {

	@Autowired
	ProductDAO productDAO;

	@Autowired
	CategoryDAO categoryDAO;

	@Autowired
	InvoiceDAO invoiceDAO;

	@Autowired
	SubCategoryDAO subCategoryDAO;

	@Autowired
	SubCatergoryRepository subCatergoryRepository;

	@Autowired
	SizeDAO sizeDAO;
	@Autowired
	ColorDAO colorDAO;
	@Autowired
	SupplierDAO supplierDAO;
	@Autowired
	HttpSession session;
	@Autowired
	FilesStorageService storageService;
	@Autowired
	UserDAO userDAO;

	@Autowired
	UserRepository userRepository;

	@GetMapping("")
	public ModelAndView getView(Model model) {
		Locale localeVN = new Locale("vi", "VN");
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String ngay = dateFormat.format(date);
		NumberFormat vn = NumberFormat.getInstance(localeVN);
		Float doanhThuNam = productDAO.doanhThuTrongNam(Year.now().getValue()) == null ? 0
				: productDAO.doanhThuTrongNam(Year.now().getValue());
		Float doanhThuNgay = productDAO.doanhThuTrongNgay(ngay) == null ? 0 : productDAO.doanhThuTrongNgay(ngay);
		System.out.println(doanhThuNgay);
		model.addAttribute("doanhThuNgay", vn.format(doanhThuNgay));
		model.addAttribute("doanhThuNam", vn.format(doanhThuNam));
		List<Float> valueCur = new ArrayList<>();
		List<Float> valuePre = new ArrayList<>();
		for (int i = 1; i <= 12; i++) {
			Float get = sizeDAO.tongTienTungThangTheoNam(Year.now().getValue(), i) == null ? 0
					: sizeDAO.tongTienTungThangTheoNam(Year.now().getValue(), i);
			valueCur.add(get);
		}
		for (int i = 1; i <= 12; i++) {
			Float get = sizeDAO.tongTienTungThangTheoNam((Year.now().getValue() - 1), i) == null ? 0
					: sizeDAO.tongTienTungThangTheoNam((Year.now().getValue() - 1), i);
			valuePre.add(get);
		}

		model.addAttribute("curDate", Year.now().getValue());
		model.addAttribute("valueCur", valueCur);
		model.addAttribute("valuePre", valuePre);

		return new ModelAndView("/admin/index");
	}

	@GetMapping("login")
	public ModelAndView getViewLogin() {
		return new ModelAndView("redirect:/admin/login");
	}

	// Long
	@RequestMapping(value = { "quanlysanpham" })
	public ModelAndView getViewSP(Model model,
			@RequestParam("p") Optional<Integer> p,
			@RequestParam("search") Optional<String> search) {
		if (search.isPresent()) {
			setSessionAdmin();
			Pageable pageable = PageRequest.of(p.orElse(0), 10);
			Page<Product> list = productDAO.toListProductBySearch(0, "%" + search.get() + "%", pageable);
			model.addAttribute("product", list);
			model.addAttribute("productOJ", new Product());
			session.setAttribute("search", search.get());
			return new ModelAndView("/admin/quanlysanpham");

		} else {
			session.removeAttribute("search");
		}
		setSessionAdmin();
		Pageable pageable = PageRequest.of(p.orElse(0), 10);
		Page<Product> list = productDAO.toListProduct(0, pageable);

		model.addAttribute("product", list);
		model.addAttribute("productOJ", new Product());
		return new ModelAndView("/admin/quanlysanpham");

	}

	@Transactional
	@RequestMapping("quanlysanpham/create")
	public ModelAndView createPD(Model model,
			@RequestParam("p") Optional<Integer> p, HttpSession session,
			@RequestParam("newOrEdit") String op,
			@Valid Product product, BindingResult bindingResult,
			@RequestParam("image") MultipartFile file) {

		String path = Paths.get(".").normalize().toAbsolutePath()
				+ "\\src\\main\\resources\\static\\product\\image\\";
		// kiểm lỗi null input file
		if (op.equals("new")) {
			product.setImage(file.getOriginalFilename());
		}

		try {
			if (op.equals("new")) {
				System.out.println("File new");
				product.setImage(file.getOriginalFilename());
				file.transferTo(new File(path + product.getImage()));
			} else {
				System.out.println("File edit");
				product.setImage(product.getId() + ".jpg");
				if (!file.isEmpty()) {
					file.transferTo(new File(path + product.getId() + ".jpg"));
				}

			}

		} catch (Exception e) {
			System.out.println("ERROR FILE: " + e.getMessage());
		}

		setSessionAdmin();

		product.setDeleted(0);
		productDAO.save(product);

		Pageable pageable = PageRequest.of(p.orElse(0), 10);
		Page<Product> list = productDAO.toListProduct(0, pageable);
		model.addAttribute("product", list);
		model.addAttribute("productOJ", new Product());

		return new ModelAndView("redirect:/admin/quanlysanpham");

	}

	// @RequestMapping("quanlynhacungcap")
	// public ModelAndView getViewSupplier() {

	// return new ModelAndView("/admin/quanlynhacungcap");

	// }

	@RequestMapping("quanlysanpham/delete")
	public ModelAndView deletePD(Model model,
			@RequestParam("p") Optional<Integer> p, HttpSession session,
			@RequestParam("delID") Optional<Integer> delID) {

		productDAO.deleteID(delID.get());
		setSessionAdmin();

		Pageable pageable = PageRequest.of(p.orElse(0), 10);
		Page<Product> list = productDAO.toListProduct(0, pageable);
		model.addAttribute("product", list);
		model.addAttribute("productOJ", new Product());
		return new ModelAndView("/admin/quanlysanpham");

	}

	// Long

	@RequestMapping("quanlysanpham/click")
	public @ResponseBody String clickPageNumber(Model model,
			@RequestParam("p") Integer p) {
		Pageable pageable;
		Page<Product> list;
		Locale localeVN = new Locale("vi", "VN");
		NumberFormat vn = NumberFormat.getInstance(localeVN);
		if (session.getAttribute("search") != null) {
			setSessionAdmin();
			pageable = PageRequest.of(p, 10);
			list = productDAO.toListProductBySearch(0, "%" + session.getAttribute("search") + "%", pageable);

		} else {
			pageable = PageRequest.of(p, 10);
			list = productDAO.toListProduct(0, pageable);
		}
		// Page<Product> list = productDAO.findAll(pageable);
		List<Product> sp = list.toList();
		String html = "";
		String tdText = "<td class='text-center'><span class='fw-bold text-dark'";

		for (Product i : sp) {

			html += "<tr>";
			html += "<td><i class='fab fa-angular fa-lg text-danger me-3'></i>"
					+ "<strong>" + i.getId() + "</strong>" + "</td>";
			html += "<td style='height: 100px !important;'>" +
					"<ul" +
					" class='list-unstyled users-list m-0 avatar-group d-flex align-items-center'>" +
					"<li data-bs-toggle='tooltip' data-popup='tooltip-custom'" + " data-bs-placement='top'"
					+ "class='avatar avatar-xs pull-up d-flex align-items-center'" + "title='Lilian Fuller'><img" +
					" src='/product/image/" + i.getImage() +
					"' alt='Avatar' class='rounded-circle'></li>" + "</ul>" + "</td>";
			html += "<td class='text-center'><span class='fw-bold text-dark'>" + i.getName() + "</span></td> ";
			html += " <td class='text-center'><span class='fw-bold text-dark'>"
					+ (i.getDiscount() == null ? 0 : i.getDiscount()) + "</span></td>";
			html += tdText + ">" + i.getSize().getName() + "</span></td>"
					+ tdText + ">" + i.getColor().getName() + "</span></td>"
					+ tdText + ">" + i.getAmount() + "</span></td>"
					+ tdText + ">" + vn.format(i.getPrice()) + "</span> đ</td>";
			html += "<td class='text-center'>" +
					"<div class='dropdown'>" +
					"<button type='button'" +
					"class='btn p-0 dropdown-toggle hide-arrow'" +
					"data-bs-toggle='dropdown'>" +
					"<i class='bx bx-dots-vertical-rounded'></i>" +
					"</button>" +
					"<div class='dropdown-menu'>" +

					"<a class='dropdown-item' data-bs-toggle='offcanvas'" +
					"onclick=fill(" + i.getId() + ") " +
					"data-bs-target='#offcanvasBackdrop'" +
					"aria-controls='offcanvasBackdrop'> <i " +
					"class='bx bx-edit-alt me-2'></i>Sửa" +
					"</a> <a class='dropdown-item'" +
					"href='javascript:void(0);'><i" +
					"class='bx bx-trash me-2'></i> Xóa</a>" +
					"</div></div></td>";
			html += "</tr>";
		}
		return html;
	}

	// Long
	@RequestMapping("/quanlysanpham/ajaxData")
	public @ResponseBody String senData(HttpServletRequest request, @RequestParam("id") Integer id)
			throws JsonProcessingException {

		Optional<Product> op = productDAO.findById(id);
		Product pd = op.get();

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json1 = ow.writeValueAsString(pd);

		return json1;

	}

	// QUAN LY DANH MUC
	// Manh
	@RequestMapping("/quanlydanhmuc")

	public ModelAndView getViewDM(Model model, @RequestParam("page") Optional<Integer> page) {
		Pageable pageable = PageRequest.of(page.orElse(0), 5);
		Page<Category> categories = categoryDAO.findAll(pageable);
		model.addAttribute("category", categories);

		Category newcategory = new Category();
		model.addAttribute("newcategory", newcategory);

		return new ModelAndView("/admin/quanlydanhmuc");
	}

	// Manh
	@RequestMapping("/quanlydanhmuc/click")
	public @ResponseBody String clickDMPageNumber(Model model, @RequestParam("page") Integer page) {

		Pageable pageable = PageRequest.of(page, 5);
		Page<Category> list = categoryDAO.findAll(pageable);
		List<Category> ca = list.toList();
		String html = "";

		for (Category i : ca) {
			html += "<tr>";
			html += "<td><span>" + i.getId() + "</span></td>";
			html += "<td><span>" + i.getCategoryName() + "</span></td>";
			html += "<td><button ng-click='fill(" + i.getId()
					+ ")'\n type=\"button\" class=\"btn btn-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#edit\">Sửa</button></td>";
			// html += "<td><button type=\"button\" class=\"btn btn-primary\"
			// ng-click='showDMC("+ i.getId() +")' >Xem</button></td>";
			html += "<td><a class=\"btn btn-primary\" href='/admin/quanlydanhmuc/delete?id=" + i.getId()
					+ "'>Xóa</a></td>";
			html += "<tr>";
		}
		return html;
	}

	// Manh
	@RequestMapping(value = "quanlydanhmuc/save", method = RequestMethod.POST)
	public ModelAndView createCategory(Model model, @ModelAttribute("newcategory") Category newcategory) {
		categoryDAO.save(newcategory);
		return new ModelAndView("redirect:/admin/quanlydanhmuc	");
	}

	// Manh
	@RequestMapping("quanlydanhmuc/delete")
	public ModelAndView deleteCategory(Model model, @RequestParam("id") Integer id) {
		List<SubCategory> list = subCatergoryRepository.findByCategory(categoryDAO.findById(id).get());

		if (!list.isEmpty()) {
			for (SubCategory subCategory : list) {
				subCategory.setCategory(null);
				subCategoryDAO.save(subCategory);
			}
		}

		categoryDAO.delete(categoryDAO.getById(id));

		return new ModelAndView("redirect:/admin/quanlydanhmuc");
	}

	// Manh
	@RequestMapping("/quanlydanhmuc/ajaxData")
	public @ResponseBody String sendDataCategoryClicked(HttpServletRequest request, @RequestParam("id") Integer id)
			throws JsonProcessingException {
		Category categoryClicked = categoryDAO.findById(id).get();

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(categoryClicked);

		return json;
	}

	// QUAN LY DANH MUC CON
	// Manh
	@RequestMapping("quanlydanhmuccon")
	public ModelAndView getViewDMC(Model model, @RequestParam(name = "id", defaultValue = "0") Integer id) {
		List<Category> listCategory = categoryDAO.findAll();
		model.addAttribute("listCategory", listCategory);

		Integer selectedCategory = categoryDAO.getById(id).getId();
		model.addAttribute("selectedCategory", selectedCategory);

		SubCategory newcsubcategory = new SubCategory();
		model.addAttribute("newcsubcategory", newcsubcategory);

		List<SubCategory> listSubCategoryToShow = new ArrayList<>();
		if (id == 0) {

			List<SubCategory> all = subCategoryDAO.findAll();
			for (SubCategory subCategory : all) {
				if (subCategory.getCategory() == null) {
					listSubCategoryToShow.add(subCategory);
				}
			}
		} else {
			listSubCategoryToShow = subCatergoryRepository.findByCatID(selectedCategory);
		}

		model.addAttribute("listSubCategoryToShow", listSubCategoryToShow);

		return new ModelAndView("/admin/quanlydanhmuccon");
	}

	// Manh
	@RequestMapping("/quanlydanhmuccon/ajaxData")
	public @ResponseBody String sendDataSubCategoryClicked(HttpServletRequest request, @RequestParam("id") Integer id)
			throws JsonProcessingException {
		SubCategory subCategoryClicked = subCategoryDAO.findById(id).get();

		System.out.println(subCategoryClicked.getId());

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(subCategoryClicked);
		return json;
	}

	// Manh
	@RequestMapping(value = "/quanlydanhmuccon/save", method = RequestMethod.POST)
	public ModelAndView updateSubCategory(Model model, @RequestParam("idCategory") Integer idCategory,
			@ModelAttribute("newcsubcategory") SubCategory newcsubcategory) {
		newcsubcategory.setCategory(categoryDAO.getById(idCategory));
		subCategoryDAO.save(newcsubcategory);
		return new ModelAndView("redirect:/admin/quanlydanhmuccon?id=" + idCategory);
	}

	// Manh
	@RequestMapping("quanlydanhmuccon/delete")
	public ModelAndView deleteSubCategory(Model model, @RequestParam("id") Integer id) {
		List<Product> list = productDAO.findAll();

		if (!list.isEmpty()) {
			for (Product product : list) {
				if (product.getSubcat().getId() == id) {
					product.setSubcat(null);
					productDAO.save(product);
				}
			}
		}

		subCategoryDAO.delete(subCategoryDAO.getById(id));

		return new ModelAndView("redirect:/admin/quanlydanhmuccon");
	}

	// QUAN LY TAI KHOAN
	// Manh
	@RequestMapping("quanlytaikhoan")
	public ModelAndView getViewUser(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam(name = "email", defaultValue = "") String email) {
		Pageable pageable = PageRequest.of(page.orElse(0), 5);
		Page<User> users;
		if (!email.isEmpty()) {
			users = userRepository.findByEmail(email, pageable);
		} else {
			users = userDAO.findAll(pageable);
		}
		model.addAttribute("users", users);

		User newuser = new User();
		model.addAttribute("newuser", newuser);

		return new ModelAndView("admin/quanlytaikhoan");
	}

	// Manh
	@RequestMapping("quanlytaikhoan/click")
	public @ResponseBody String clickUserPageNumber(Model model, @RequestParam("page") Integer page) {

		Pageable pageable = PageRequest.of(page, 5);
		Page<User> list = userDAO.findAll(pageable);
		List<User> users = list.toList();
		String html = "";

		for (User i : users) {

			html += "<tr>";
			html += "<td><span>" + i.getId() + "</span></td>";
			html += "<td><span>" + i.getPasswords() + "</span></td>";
			html += "<td><span>" + i.getName() + "</span></td>";
			html += "<td><span>" + i.getEmail() + "</span></td>";
			html += "<td><span>" + i.getAddress() + "</span></td>";
			html += "<td><span>" + i.getDob() + "</span></td>";
			html += "<td><span>" + (i.getGender() ? "Nam" : "Nữ") + "</span></td>";
			html += "<td><span>" + i.getPhone() + "</span></td>";
			html += "<td><span>" + i.getRole() + "</span></td>";
			html += "<td><span>" + (i.getActived() ? "Active" : "Closed") + "</span></td>";
			html += "<td>\r\n"
					+ "			<div class=\"dropdown\">\r\n"
					+ "				<button type=\"button\"\r\n"
					+ "					class=\"btn p-0 dropdown-toggle hide-arrow\"\r\n"
					+ "					data-bs-toggle=\"dropdown\">\r\n"
					+ "					<i class=\"bx bx-dots-vertical-rounded\"></i>\r\n"
					+ "				</button>\r\n"
					+ "				<div class=\"dropdown-menu\">\r\n"
					+ "\r\n"
					+ "					<a "
					+ "						ng-click='fill(" + i.getId() + ")'\r\n"
					+ "						class=\"dropdown-item\" data-bs-toggle=\"offcanvas\"\r\n"
					+ "						data-bs-target=\"#offcanvasBackdrop\"\r\n"
					+ "						aria-controls=\"offcanvasBackdrop\"> <i\r\n"
					+ "						class=\"bx bx-edit-alt me-2\"></i>Sửa\r\n"
					+ "					</a> <a href=/admin/quanlytaikhoan/delete?id=" + i.getId()
					+ " class=\"dropdown-item\"><i\r\n"
					+ "						class=\"bx bx-trash me-2\"></i>Delete</a>\r\n"
					+ "				</div>\r\n"
					+ "			</div>\r\n"
					+ "		</td>";
			html += "</tr>";
		}

		return html;
	}

	// Manh
	@RequestMapping(value = "quanlytaikhoan/create", method = RequestMethod.POST)
	public ModelAndView createUser(Model model, @ModelAttribute("newuser") User newuser) {
		userDAO.save(newuser);
		return new ModelAndView("redirect:/admin/quanlytaikhoan");
	}

	// Manh
	@RequestMapping("quanlytaikhoan/delete")
	public ModelAndView deleteUser(Model model, @RequestParam("id") Integer id) {
		User thisUser = userDAO.findById(id).get();
		thisUser.setActived(false);
		userDAO.save(thisUser);
		return new ModelAndView("redirect:/admin/quanlytaikhoan");
	}

	// Manh
	@RequestMapping("/quanlytaikhoan/ajaxData")
	public @ResponseBody String sendDataUserClicked(HttpServletRequest request, @RequestParam("id") Integer id)
			throws JsonProcessingException {
		User userClicked = userDAO.findById(id).get();

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(userClicked);

		return json;
	}

	// phu
	@RequestMapping("quanlyhoadon")
	public ModelAndView getViewHD(Model model) {

		List<Invoice> listInvoice = invoiceDAO.findAll();
		model.addAttribute("invoices", listInvoice);

		return new ModelAndView("/admin/quanlyhoadon");
	}

	// phu
	@RequestMapping("/quanlyhoadon/ajaxData1")
	public @ResponseBody String viewInvoiceDetail(@RequestParam("id") Integer id) throws JsonProcessingException {

		Invoice in = invoiceDAO.findById(Integer.valueOf(id)).get();

		List<InvoiceDetail> list = in.getListInvoiceDetail();

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(list);
		return json;

	}

	// phu
	@RequestMapping("/quanlyhoadon/search")
	public @ResponseBody String searchInvoiceByUser(@RequestParam("name") String userName)
			throws JsonProcessingException {
		List<Invoice> in = new ArrayList<>();
		List<User> lu = userDAO.findByUserName(userName);
		List<Invoicep> inpl = new ArrayList();
		if (userName.trim() == null || userName.trim() == "" || userName.trim().isEmpty()) {
			in = invoiceDAO.findAll();

			for (int i = 0; i < in.size(); i++) {
				Invoicep inp = new Invoicep();
				inp.setId(in.get(i).getId());
				inp.setCreationTime(in.get(i).getCreationTime());
				inp.setDeliveryAddress(in.get(i).getDeliveryAddress());
				inp.setListInvoiceDetail(in.get(i).getListInvoiceDetail());
				inp.setPaymentMethodId(in.get(i).getPaymentMethodId());
				inp.setStatus(in.get(i).getStatus());
				inp.setUser(in.get(i).getUser());
				inp.setVoucher_user_id(in.get(i).getVoucher_user_id());

				Double tong = 0.0;
				if (inp.getListInvoiceDetail() != null) {
					List<InvoiceDetail> list = inp.getListInvoiceDetail();
					for (int j = 0; j < list.size(); j++) {
						tong += inp.getListInvoiceDetail().get(j).getQuantity()
								* inp.getListInvoiceDetail().get(j).getProduct().getPrice();
					}
				}

				inp.setTongTien(tong);

				inpl.add(inp);
			}
		} else {
			if (lu.size() > 0) {
				User u = lu.get(0);
				in = u.getListInvoice();
				if (in.size() > 0) {
					for (int i = 0; i < in.size(); i++) {
						Invoicep inp = new Invoicep();

						inp.setId(in.get(i).getId());
						inp.setCreationTime(in.get(i).getCreationTime());
						inp.setDeliveryAddress(in.get(i).getDeliveryAddress());
						inp.setListInvoiceDetail(in.get(i).getListInvoiceDetail());
						inp.setPaymentMethodId(in.get(i).getPaymentMethodId());
						inp.setStatus(in.get(i).getStatus());
						inp.setUser(in.get(i).getUser());
						inp.setVoucher_user_id(in.get(i).getVoucher_user_id());

						Double tong = 0.0;
						if (inp.getListInvoiceDetail() != null) {
							List<InvoiceDetail> list = inp.getListInvoiceDetail();
							for (int j = 0; j < list.size(); j++) {
								tong += inp.getListInvoiceDetail().get(j).getQuantity()
										* inp.getListInvoiceDetail().get(j).getProduct().getPrice();
							}
						}

						inp.setTongTien(tong);
						inpl.add(inp);
					}
				}
			}
		}

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(inpl);
		return json;

	}

	public void setSessionAdmin() {

		session.setAttribute("category", categoryDAO.findAll());
		session.setAttribute("subcategory", subCategoryDAO.findAll());
		session.setAttribute("size", sizeDAO.findAll());
		session.setAttribute("supplier", supplierDAO.findAll());
		session.setAttribute("color", colorDAO.findAll());
	}

}
