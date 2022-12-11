package com.web.cart;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.web.base.BaseRestController;
import com.web.cart.CartDetail.CartDetailQuantityIsZero;
import com.web.data.CartDetailRepository;
import com.web.data.service.ShoppingSessionService;
import com.web.product.Product;
import com.web.util.JSONConverter;

@RestController
// @RequestMapping("/${cart.secure.path}")
public class CartController extends BaseRestController<CartController> {

	@Autowired
	ShoppingSessionService shopService;

	@Autowired
	@Qualifier("cartdrepo")
	CartDetailRepository cartDao;

	@GetMapping("/cart/empty")
	public String empty() {
		return "cart-empty";
	}

	// @GetMapping("/account/cart")
	// public ModelAndView viewC() {
	// return getModalAndView();
	// }

	@GetMapping("/account/cart")
	public ModelAndView view() {
		return getModalAndView();
	}

	//
	@GetMapping("/api/cd")
	public ShoppingSession getCartDetailByUserId(@RequestParam("uid") Integer id) {

		// shoppingDao.
		return shopService.findByUserID(id).orElse(null);
	}

	@GetMapping("/api/findpincart")
	public Product getProductInCart(@RequestParam("sid") Integer sid, @RequestParam("pid") Integer pid) {
		Optional<CartDetail> p = cartDao.findProductInCart(sid, pid);

		if (p.isPresent())
			return p.get().getProduct();

		return null;
	}

	@GetMapping("/cart/cdws")
	public List<CartDetail> getCartDetailBySession(@RequestParam("ss") String session) {

		// shoppingDao.
		List<CartDetail> list = shopService.findCartDetailWithSession(session);
		System.out.println(list);
		return list;
	}

	@GetMapping("/api/cdws")
	public ResponseEntity<?> getCartDetailBySession1(@RequestParam("ss") String session) {

		List<CartDetail> list = shopService.findCartDetailWithSession(session);
		System.out.println(list);
		if (list == null)
			return new ResponseEntity<String>("Không tìm thấy giỏ hàng", HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<String>(JSONConverter.convertList(list), HttpStatus.OK);

	}

	@GetMapping("/api/cartdetail")
	public List<CartDetail> getCartDetail() {

		return cartDao.findAll();
	}

	@GetMapping("/cart/addtocart")
	public boolean addProductToCart(@RequestParam("pid") Integer proId, @RequestParam("ss") String session) {
		boolean isAdded = shopService.addProductToCart(proId, session);
		System.out.println("isAdded : " + isAdded);
		return isAdded;
	}

	@GetMapping("/cart/updatetocart")
	public boolean eidtProductInCart(@RequestParam("pid") Integer pid, @RequestParam("qty") Integer qty,
			@RequestParam("sid") String session) {
		try {
			boolean isUpdate = shopService.updateProductInCart(pid, qty, session);
			System.out.println("isUpdate = " + isUpdate);
			return isUpdate;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@GetMapping({ "/api/updatetocart" })
	public ResponseEntity<?> updateProductToCartAPI(@RequestParam("pid") Integer pid, @RequestParam("qty") Integer qty,
			@RequestParam("ss") String session) {
		boolean isUpdate = eidtProductInCart(pid, qty, session);
		System.out.println("isUpdate: " + isUpdate);

		return isUpdate ? new ResponseEntity<String>("Đã thêm vào giỏ ", HttpStatus.OK)
				: new ResponseEntity<String>("Lỗi: Thêm thất bại", HttpStatus.BAD_REQUEST);

		// return isAdded;
	}

	@GetMapping({ "/api/addtocart" })
	public ResponseEntity<?> addProductToCartAPI(@RequestParam("pid") Integer proId,
			@RequestParam("ss") String session) {
		boolean isAdded = shopService.addProductToCart(proId, session);
		System.out.println("isAdded : " + isAdded);

		return isAdded ? new ResponseEntity<String>("Đã thêm vào giỏ ", HttpStatus.OK)
				: new ResponseEntity<String>("Lỗi: Thêm thất bại", HttpStatus.BAD_REQUEST);

		// return isAdded;
	}

	@GetMapping("/api/reducetocart")
	public boolean reduceProductToCart(@RequestParam("pid") Integer proId, @RequestParam("ss") String session) {
		boolean isAdded = shopService.reduceProductInCart(proId, session);
		System.out.println("isreduced : " + isAdded);
		return isAdded;
	}

	// @RequestMapping(value = "/api/updateItem" , method =RequestMethod.POST)
	// ‚@RequestMapping(value = "/api/updateItem" , method =RequestMethod.POST)
	@PostMapping("/api/updateItem")
	public ResponseEntity<String> updateCartItem(@RequestBody String item) {
		// System.out.println("post item: "+item);

		CartDetail x = null;
		try {
			CartDetail cd = getFromJson(item);
			x = cartDao.getById(cd.getId());
			System.out.println(cd);
			// CartDetail x = cartDao.getById(cd.getId());
			x.updateQuantity(cd.getQuantity());
			cartDao.save(x);
			System.out.println("save");
		} catch (CartDetailQuantityIsZero e) {
			// e.printStackTrace();
			cartDao.delete(x);
			System.out.println("remove");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Lỗi: Cập nhật thất bại", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("fine", HttpStatus.OK);
	}

	@PostMapping("/api/deleteItem")
	public ResponseEntity<String> deleteCartItem(@RequestBody String item) {
		try {
			CartDetail cd = getFromJson(item);
			CartDetail cartItem = cartDao.getById(cd.getId());
			if (cartItem == null)
				throw new RuntimeException("Không tồn tại ");
			cartDao.delete(cartItem);
		} catch (Exception e) {
			e.printStackTrace();
			String mess = "Lỗi: Xoá thất bại";
			if (e instanceof RuntimeException)
				mess = "Lỗi: " + e.getMessage();
			return new ResponseEntity<String>(mess, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>("fine", HttpStatus.OK);
	}

	public Gson getGsonObj() {
		Gson gson = new GsonBuilder()
				.addDeserializationExclusionStrategy(new ExclusionStrategy() {
					@Override
					public boolean shouldSkipField(FieldAttributes f) {
						return f.getName().toLowerCase().contains("fieldName");
					}

					@Override
					public boolean shouldSkipClass(Class<?> aClass) {
						return false;
					}
				})
				.create();
		return gson;
	}

	public CartDetail getFromJson(String item) {
		try {
			Gson gson = getGsonObj();
			CartDetail x = null;
			CartDetail cd = gson.fromJson(item, CartDetail.class);
			return cd;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Lỗi convert Json to Pojo");
		}

	}
}
