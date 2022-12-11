package com.web.signup;

import java.util.Date;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.web.base.BaseRestController;
import com.web.data.UserRepository;
import com.web.login.LoginController;
import com.web.user.User;

@Controller
@RequestMapping("/signup")
public class signupController extends BaseRestController<signupController> {

	@Autowired
	UserRepository userRepository;

	LoginController lgctrl;

	// thao
	@GetMapping
	public String getView(Model model) {
		System.out.println("lgoin : form " + lgctrl.isAuthenticated());
		if (lgctrl.isAuthenticated()) {
			return "redirect:/";
		}
		User user = new User();
		user.setAddress("Chưa nhập địa chỉ");
		user.setDob(new Date());
		model.addAttribute("user", user);
		return "signup";
	}

	// // kiểm tra và đăng ký
	// @PostMapping("/register")
	// public String register(@ModelAttribute("user") User user) {
	// // log.info(username);
	// user.setRole("ROLE_USER");
	// user.setDob(null);
	// user.setGender(null);
	// user.setPhone(null);
	// user.setActived(true);
	// user.setAddress(null);
	// user.getSession();
	// System.out.println(user);
	// userRepository.save(user);
	// System.out.println("thêm thành công##########################");
	// return "redirect:/login";
	// }

	// thao
	@PostMapping
	public ModelAndView register(@Valid @ModelAttribute("user") User user, BindingResult binding, Model model) {
		if (binding.hasErrors()) {
			// model.addAttribute("me", "Đăng ký thất bại");
			System.out.println("--------------------------Error: Register-------------------\n" + binding.toString());
			return getModalAndView("signup");
		}
		try {
			if (addUser(user)) {
				// model.addAttribute("me", "Đăng ký thàng công");
				System.out.println("--------------------------Sucess: Register-------------------");
				// Thread.sleep(2000);
				return getModalAndView("redirect:/login");
			} else {
				// model.addAttribute("me", "Đăng ký thất bại");
				System.out.println("--------------------------Error: Register-------------------");
				// Thread.sleep(2000);
				return getModalAndView();
			}
		} catch (Exception e2) {
			// model.addAttribute("me", "Đăng ký thất bại");
			System.out.println("--------------------------Error: Register-------------------\n" + e2);
			return getModalAndView();
		}
	}

	// thao
	public boolean addUser(@ModelAttribute("user") User user) {
		try {
			user.setRole("ROLE_USER");
			user.setGender(true);
			user.setPhone(null);
			user.setActived(true);
			userRepository.save(user);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
}