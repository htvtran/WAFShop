package com.web.user;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.web.base.BaseRestController;
import com.web.data.UserRepository;

@RestController
// @RequestMapping("/account")
public class AccountController extends BaseRestController<AccountController> {

	private static Authentication auth;

	@Autowired
	@Qualifier("urepo")
	UserRepository dao;

	@RequestMapping("/account")
	public RedirectView view2() {

		return new RedirectView("/profile");
	}
	
	
	// Thao
	@GetMapping("/account/profile")
	public ModelAndView view(Model model) {
		auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		Optional<User> op = dao.findByEmail(userName);
		User user = op.get();
		model.addAttribute("user", user);
		return getModalAndView();
	}

	// thao
	@PostMapping("/account/profile")
	public ModelAndView update(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return getModalAndView();
		}
		try {
			auth = SecurityContextHolder.getContext().getAuthentication();
			String userName = auth.getName();
			Optional<User> op = dao.findByEmail(userName);
			User us = op.get();
			user.setId(us.getId());
			user.setRole("ROLE_USER");
			user.setActived(true);
			user.setPasswords(us.getPasswords());
			dao.save(user);
			System.out.println("Update profile success");
		} catch (Exception e) {
			System.out.println("Update profile fail");
		}
		return getModalAndView();
	}

	// thao
	@PostMapping("/account/cancel")
	public ModelAndView cancel(Model model) {
		auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		Optional<User> op = dao.findByEmail(userName);
		User user = op.get();
		model.addAttribute("user", user);
		return getModalAndView();
	}

	@GetMapping("/api/list")
	public List<User> getList() {
		return dao.findAll();

	}

}
