package com.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.web.base.BaseRestController;
import com.web.category.Category;
import com.web.data.CatergoryRepository;
import com.web.data.SubCatergoryRepository;
import com.web.login.LoginController;
import com.web.product.Product;
import com.web.subcategory.SubCategory;

@Controller
// @SessionAttributes("catList")
@ControllerAdvice
public class MainController extends BaseRestController<MainController> {
	//
	@Autowired
	LoginController auth;

	@Autowired
	@Qualifier("catrepo")
	CatergoryRepository catDao;

	@Autowired
	@Qualifier("subcatrepo")
	SubCatergoryRepository subCatDao;

	@GetMapping(value = { "/", "" })
	public String getView(Model model, HttpSession session) {

		return "home";
	}

	@GetMapping("/404")
	public String getErrorView() {
		return "404";
	}

	@GetMapping("/search")
	public ModelAndView getSearch(HttpSession session) {
		// session.setAttribute("catList", getCatList());
		// session.setAttribute("subDao", subCatDao);
		return new ModelAndView("home_search");
	}

	//
	// @Autowired
	// @Qualifier("catrepo")
	// CatergoryRepository catDao;
	//
	// @Autowired
	// @Qualifier("subcatrepo")
	// SubCatergoryRepository subCatDao;
	//
	//
	@ModelAttribute("catList")
	public List<Category> getCatList() {
		return catDao.findAll();
	}

	@ModelAttribute
	public SubCatergoryRepository getSubDao() {
		return subCatDao;
	}

	@ModelAttribute("subList")
	public List<SubCategory> getSubList() {
		return subCatDao.findAll();
	}

	public List<SubCategory> getSubListOf(Integer id) {
		return subCatDao.findByCatID(id);
	}

	public List<Product> getProListFromSubCat(Integer id) {
		List<SubCategory> subList = subCatDao.findAllById(List.of(id));

		return subList.isEmpty() ? null : subList.get(0).getProList();
	}

}
