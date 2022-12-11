package com.web.config;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.HandlerInterceptor;

import com.web.category.Category;
import com.web.data.CatergoryRepository;
import com.web.data.SubCatergoryRepository;
import com.web.data.service.ShoppingSessionService;
import com.web.login.LoginController;
import com.web.product.Product;
import com.web.subcategory.SubCategory;
import com.web.util.CurrencyFormatter;

import lombok.NoArgsConstructor;

// @RequiredArgsConstructor
@NoArgsConstructor
@Component("myin")
public class MyInterceptor implements HandlerInterceptor {

	@Autowired
	@Qualifier("catrepo")
	CatergoryRepository catDao;

	@Autowired
	@Qualifier("subcatrepo")
	SubCatergoryRepository subCatDao;

	@Autowired
	@Qualifier("sservice")
	ShoppingSessionService ss;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();

		// System.out.println("session names: " +
		// session.getAttributeNames().toString()) ;
		// session.setAttribute("cartSize", 0);

		Object catlist = session.getAttribute("catList");
		Object subDao = session.getAttribute("subDao");

		if (catlist == null || subDao == null) {
			System.out.println("create session attribute");
			session.setAttribute("catList", getCatList());
			session.setAttribute("subDao", subCatDao);
			session.setAttribute("cformatter", new CurrencyFormatter());
		}

		if (isAuthenticated()) {
			String sid = session.getId();
			int size = ss.findCartDetailWithSession(sid).size();
			session.setAttribute("cartSize", size);

		}

		return true;
	}

	@ModelAttribute("catList")
	public List<Category> getCatList() {
		return catDao.findAll();
	}

	@ModelAttribute
	public SubCatergoryRepository getSubDao() {
		return subCatDao;
	}

	public List<SubCategory> getSubListOf(Integer id) {
		return subCatDao.findByCatID(id);
	}

	public List<Product> getProListFromSubCat(Integer id) {
		List<SubCategory> subList = subCatDao.findAllById(List.of(id));

		return subList.isEmpty() ? null : subList.get(0).getProList();
	}

	public static boolean isAuthenticated() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth == null || AnonymousAuthenticationToken.class.isAssignableFrom(auth.getClass())) {
			return false;
		}
		return auth.isAuthenticated();
	}

}
