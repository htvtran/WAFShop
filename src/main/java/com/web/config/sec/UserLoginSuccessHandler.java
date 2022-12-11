package com.web.config.sec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.web.data.service.ShoppingSessionService;
import com.web.de.MyUserDetails;
import com.web.user.User;

@Component
@ComponentScan(basePackages = "com.web.data.service")
public class UserLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	@Qualifier("sservice")
	ShoppingSessionService ss;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();

		String session = request.getRequestedSessionId();
		User u = userDetails.getUser();

		//
		// System.out.println("IN WUW session" + request.getRequestedSessionId());
		// System.out.println("lqlql user " + userDetails.getUser());
		//
		// System.out.println(ss==null) ;
		////
		ss.updateSession(session, u);
		System.out.println("update success");

		super.onAuthenticationSuccess(request, response, authentication);

	}

}
