package com.web.category;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.web.base.BaseRestController;
import com.web.data.CatergoryRepository;
import com.web.product.Product;
import com.web.subcategory.SubCategory;

//@RestController

@PropertySource("classpath:properties/global.properties")
@RequestMapping("/category")
@RestController
public class CategoryController extends BaseRestController<CategoryController> {

	@Autowired
	CatergoryRepository catDao;

	private final int DEFAULT_SHOW_PRODUCT_NUMBER = 9;

	@Value("${con.secure.path}")
	private List<String> list;

	@GetMapping("")
	public ModelAndView view() {
		return new ModelAndView("redirect:category/1");
	}

	@GetMapping("{cid}")
	public ModelAndView getViewOf(@PathVariable("cid") Integer cid,
			@RequestParam(value = "p", required = false) Optional<Integer> page) {
		ModelAndView mv = getModalAndView();

		Category cat = catDao.getById(cid);
		PagedListHolder ph = getPageHolder(Optional.of(DEFAULT_SHOW_PRODUCT_NUMBER), page, getProductListOf(cat));
		List<Product> list = getList(ph);

		System.out.println(list);
		// System.out.println("page count: " + ph.getPageCount());

		mv.addObject("catObj", cat);
		mv.addObject("products", list);

		mv.addObject("page", ph);
		// ph.getPage();

		return mv;
	}

	public Integer getPageNum(Optional<Integer> pagenum, int listSize, int pageSize) {

		int requestNum = pagenum.orElse(0);

		int total = (int) Math.ceil((listSize / pageSize) * 1.0);

		if (requestNum <= 0)
			return 0;

		if (requestNum >= total)
			return total - 1;

		return requestNum - 1;
	}

	public List<Product> getProductListOf(Category cat) {
		List<Product> listPro = new ArrayList<>();

		for (SubCategory sub : cat.getSubCategory()) {
			listPro.addAll(sub.getProList());
		}
		return listPro;
	}

	public PagedListHolder<?> getPageHolder(Optional<Integer> size, Optional<Integer> pagenum, Optional<String> byField,
			List<?> list) {
		// Pageable pageable = PageRequest.of(pagenum.orElse(0), size.orElse(8),
		// Sort.by(Direction.ASC, byField.orElse("name")));
		int realpage = getPageNum(pagenum, list.size(), size.orElse(8));
		PagedListHolder<?> ph = new PagedListHolder<>(list);
		ph.setPageSize(size.orElse(8));
		ph.setSort(new MutableSortDefinition(byField.orElse("price"), true, false));
		ph.setPage(realpage);
		// ph = PageHolder.getPageOf(list, 8, realpage);
		return ph;
	}

	public List<Product> getListOfPage(Optional<Integer> size, Optional<Integer> pagenum, Optional<String> byField,
			List<?> list) {
		PagedListHolder<?> ph = getPageHolder(size, pagenum, byField, list);
		return (List<Product>) ph.getPageList();
	}

	public List<Product> getList(PagedListHolder pg) {
		return pg.getPageList();
	}

	public PagedListHolder<?> getPageHolder(Optional<Integer> size, Optional<Integer> pagenum, List<?> list) {
		return getPageHolder(size, pagenum, Optional.ofNullable(null), list);

	}

}
