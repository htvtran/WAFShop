package com.web.user;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.Quota.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.web.data.UserService;
import com.web.util.Utility;

import net.bytebuddy.utility.RandomString;

@Controller
public class LostPasswordController {
	@Autowired
	private UserService uService;
	
	
	
	@Autowired
	private JavaMailSender mailSender;
	
	@GetMapping("/lost-password")
	public String showLostPasswordForm(Model model) {
		model.addAttribute("pageTitle","Lost Password");
		return "lost-password";
	}
	
	@PostMapping("/lost-password")
	public String processLostPassword(Model model ,HttpServletRequest request ) {
		String email = request.getParameter("email");
		String token = RandomString.make(45);
		
		try {
			uService.updateResetPasswordToken(token, email);
			String resetPasswordLink = Utility.getSiteURL(request) + "/reset-password?token=" + token;
			sendEmail(email, resetPasswordLink);
			model.addAttribute("message","We have sent a password link to your email. Please check!");
		} catch (UserNotFoundException e) {
			model.addAttribute("error",e.getMessage());
		} catch (UnsupportedEncodingException e) {
			model.addAttribute("error", "Error while sending email.");
		} catch (MessagingException e) {

		} 
		
		return "lost-password";
	}
	
	@GetMapping("/reset-password")
	public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
		User u = uService.get(token);
		if(u==null) {
			model.addAttribute("title","Reset your password");
			model.addAttribute("message","Invalid Token!");
			return "message";
		}
		model.addAttribute("token",token);
		model.addAttribute("pageTitle","Reset Password");
		return "reset-password";
	}
	
	
	
	@PostMapping("/reset-password")
	public String processResetPassword(HttpServletRequest request, Model model) {
		String token = request.getParameter("token");
		String password = request.getParameter("password");
		
		User u = uService.get(token);
		if(u==null) {
//			model.addAttribute("title","Reset your password");
//			model.addAttribute("message","Invalid Token!");	
			return "reset-password";
		}else {
			System.out.println("mata khau: "+password);
			uService.updatePassword(u, password);
//			model.addAttribute("message","Change password successfully!");
			return "redirect:/login";
		}
		
		
	}
	
	private void sendEmail(String email, String resetPasswordLink) throws UnsupportedEncodingException, MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED);
		
		helper.setFrom("trungpbps19244@fpt.edu.vn", "WA-Shop support");
		helper.setTo(email);
		
		String subject = "Here's the link to reset your password";
		
		String content = "<p>Hello, </p>"
				+"<p>You have requested to reset your password</p>"
				+"<p>Click the link below to change your password:</p>"
				+"<p><b><a href=\""+resetPasswordLink+"\">Change my password here ...</a></b></p>"
				+"<img width='300px' src='cid:logoImage'/>"
				+"<p><i>Ignore this email if you remember your password, or you have not made the request.</i></p>";
		helper.setSubject(subject);
		helper.setText(content, true);
		ClassPathResource resource = new ClassPathResource("/static/images/main-logo/logo-mini.png");
		helper.addInline("logoImage", resource);
		
		mailSender.send(message);
	}
}
