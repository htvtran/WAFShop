package com.web.login;

import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.web.user.User;

@Controller
@RestController
@RequestMapping("/login")
public class LoginController {

	public static Authentication auth;

	@GetMapping("")
	public ModelAndView getView() {

		System.out.println("lgoin : form "+ isAuthenticated());
		if (isAuthenticated()) {
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("login");
	}

	public static Authentication getAuth() {
		auth = SecurityContextHolder.getContext().getAuthentication();
		return auth;
		
		
	}

	public static boolean isAuthenticated() {
		auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth == null || AnonymousAuthenticationToken.class.isAssignableFrom(auth.getClass())
				) {
			return false;
		}
		return auth.isAuthenticated();
	}
	
	//login admin
	@GetMapping("manage")
	public ModelAndView getViewLogin(HttpSession session) {
	
			
		System.out.println(LoginController.getAuth().isAuthenticated());
		System.out.println(LoginController.isAuthenticated());
		
		
		
		if(LoginController.isAuthenticated()) {
			return  new ModelAndView("redirect:/admin");
		}
		
		return new ModelAndView("admin/login");
	}

}
