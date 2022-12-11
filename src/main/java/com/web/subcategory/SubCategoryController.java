package com.web.subcategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.web.base.BaseRestController;
import com.web.category.Category;
import com.web.config.bind.Bind.Ctrl;
import com.web.data.CatergoryRepository;
import com.web.data.SubCatergoryRepository;
import com.web.product.Product;

@RestController
public class SubCategoryController extends BaseRestController<SubCategoryController> {

	@Autowired
	SubCatergoryRepository subCatDao;

	@Value("${bind.nonclass.name}")
	private String SUB_SUB_CATEGORY_BIND;

	@Autowired
	CatergoryRepository catDao;

	@GetMapping("/subcategory")
	public ModelAndView getSubCatView(@RequestParam("c") Optional<Integer> cid,
			@RequestParam("po") Optional<String> name,
			@RequestParam("p") Optional<Integer> page) {

		name.orElse("");
		if (cid.isEmpty()) {
			return new ModelAndView("redirect:/category/" + cid.orElse(1));
		} else if (name.isEmpty()) {
			return new ModelAndView("redirect:category/1");
		}

		ModelAndView mv = getModalAndView();
		Category cat = catDao.getById(cid.get());
		List<SubCategory> spList = subCatDao.getSubCatByPosition(cid.get(), name.get());
		List<Product> products = getProductListOf(spList);

		PagedListHolder ph = getPageHolder(Optional.of(8), page, products);

		List<Product> list = getList(ph);

		System.out.println(ph.getPageCount());

		System.out.println("spList " + spList.size());
		System.out.println("products " + products.size());
		System.out.println("pros " + products);
		// System.out.println("page count: " + ph.getPageCount());
		mv.addObject("catObj", cat);
		mv.addObject("products", list);
		mv.addObject("scList", spList);
		mv.addObject("page", ph);

		return mv;

		// return mv;
	}

	// sub-subcategory.
	@GetMapping("/subcategory/sub")
	// public ModelAndView getView(@RequestParam("sid") Optional<Integer> sid) {
	public ModelAndView getView(@RequestParam("si") Integer si, @RequestParam("p") Optional<Integer> page) {
		Ctrl ctrl2 = getCtrlOf(SUB_SUB_CATEGORY_BIND);
		System.out.println("nonclass t " + ctrl2.getView());

		ModelAndView mv = getModalAndView(ctrl2);
		mv.addObject("title", ctrl2.getTitle());

		SubCategory spList = subCatDao.findById(si).get();
		List<Product> products = getProductListOf(spList);

		PagedListHolder ph = getPageHolder(Optional.of(8), page, products);

		List<Product> list = getList(ph);

		// System.out.println("page count: " + ph.getPageCount());
		mv.addObject("catObj", spList.getCategory());
		mv.addObject("products", list);
		mv.addObject("subObj", spList);
		mv.addObject("page", ph);

		return mv;
	}

	// @GetMapping("/subcategory")
	// public ModelAndView view2(@RequestParam("c") Integer cid, @RequestParam("po")
	// String position,
	// @RequestParam("p") Optional<Integer> page) {
	// ModelAndView mv = getModalAndView();
	// Category cat = catDao.getById(cid.get());
	// PagedListHolder ph = getPageHolder(Optional.of(8), page,
	// getProductListOf(cat));
	// List<Product> list = getList(ph);
	// }

	@GetMapping("/api/subcategory")
	public List<SubCategory> getList() {
		return subCatDao.findAll();
	}

	@GetMapping("/api/subcategory/{cid}/{po}")
	public List<SubCategory> getByPosition(@PathVariable("cid") Integer id, @PathVariable("po") String po) {
		return subCatDao.getSubCatByPosition(id, po);
	}

	@GetMapping("/api/subcategory/{id}")
	public List<SubCategory> getSubListOf(@PathVariable("id") Integer id) {
		return subCatDao.findByCatIDs(id);
	}

	@GetMapping("/api/subcategory/find/{subid}")
	public List<SubCategory> getList(@PathVariable("subid") Integer id) {
		return subCatDao.findAllById(List.of(id));
	}

	@GetMapping("/api/subcategory/findpro/{subid}")
	public List<Product> getProListFromSubCat(@PathVariable("subid") Integer id) {
		List<SubCategory> subList = subCatDao.findAllById(List.of(id));

		return subList.isEmpty() ? null : subList.get(0).getProList();
	}

	@GetMapping("/api/subp")
	public List<SubCategoryPosition> getByPostions(@RequestParam("cid") Integer cid) {
		List<SubCategoryPosition> l = subCatDao.filterSubByPositionWithCat(cid);

		l.get(0).getMap();
		return l;
	}

	public Integer getPageNum(Optional<Integer> pagenum, int listSize, int pageSize) {

		int requestNum = pagenum.orElse(0);

		double total = Math.ceil(((double) listSize / pageSize));

		if (requestNum <= 0)
			return 0;

		if (requestNum >= total)
			return (int) total - 1;

		return requestNum - 1;
	}

	public List<Product> getProductListOf(List<SubCategory> listSub) {
		List<Product> listPro = new ArrayList<>();

		for (SubCategory sub : listSub) {
			listPro.addAll(sub.getProList());
		}
		// for (SubCategory sub : cat.getSubCategory()) {
		// listPro.addAll(sub.getProList());
		// }
		return listPro;
	}

	public List<Product> getProductListOf(SubCategory listSub) {
		return listSub.getProList();

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
		System.out.println("realp " + realpage);
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
