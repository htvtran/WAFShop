package com.web.checkout;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.web.base.BaseRestController;
import com.web.cart.Invoices;
import com.web.cart.ShoppingSession;
import com.web.data.InvoicesRepository;
import com.web.data.service.ShoppingSessionService;
import com.web.user.User;

@RestController
// @RequestMapping("${checkout.secure.path}")
public class CheckoutController extends BaseRestController<CheckoutController> {

	@Autowired
	@Qualifier("ivrepo")
	InvoicesRepository iDao;

	@Autowired
	@Qualifier("sservice")
	ShoppingSessionService shopService;

	@GetMapping("/account/checkout")
	public ModelAndView view(HttpSession session) {
		ModelAndView mv = getModalAndView();

		ShoppingSession shop = shopService.findBySession(session.getId()).orElse(null);
		Integer size = shop.getCd().size();

		mv.addObject("cartSize", size);

		return mv;
	}

	@ModelAttribute("title")
	public String title() {
		return getTitle();
	}

	@PostMapping("/account/checkout/check")
	public ResponseEntity<String> proccess(@RequestBody String list, @RequestParam("ss") String session) {

		Optional<ShoppingSession> ss = shopService.findBySession(session);
		try {
			if (ss.isEmpty())
				return new ResponseEntity<String>("Faild", HttpStatus.BAD_REQUEST);

			User u = ss.get().getUser();
			Invoices in = new Invoices(u); 
			in.setStatus(true);
			iDao.save(in);
			shopService.clearCart(u.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Faild", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>("Checked out", HttpStatus.OK);
	}
	// System.out.println();

	@PostMapping("/api/checkout")
	public ResponseEntity<String> chekout(@RequestParam("ss") String session) {
		Optional<ShoppingSession> ss = shopService.findBySession(session);
		try {
			if (ss.isEmpty())
				return new ResponseEntity<String>("Faild", HttpStatus.BAD_REQUEST);

			User u = ss.get().getUser();

			iDao.save(new Invoices(u));
			shopService.clearCart(u.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<String>("Checked out", HttpStatus.OK);
	}

}
