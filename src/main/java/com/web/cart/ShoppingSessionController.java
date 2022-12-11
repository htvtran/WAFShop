package com.web.cart;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.web.data.ShoppingSessionRepository;
import com.web.data.service.ShoppingSessionService;
import com.web.user.User;

@RestController
public class ShoppingSessionController {
	
	@Autowired
	@Qualifier("sservice")
	ShoppingSessionService sDao;
	
	@GetMapping("/api/ss")
	public List<ShoppingSession> getS() {
		
//		boolean saved = sDao.updateSession();
		
//		System.out.println(saved);
		return sDao.findAll();
	}

	
	@GetMapping("/api/update/{sid}/{uid}")
	public boolean update(@PathVariable("sid") String sid, 
			@PathVariable("uid") Integer id) {
		
		 
		boolean a = sDao.updateSession(sid, id);
//		boolean saved = sDao.updateSession();
		
//		System.out.println(saved);
		return a;
	}
	
	@GetMapping("/api/getSessionID")
	public ResponseEntity<?> getShoppingSSId(@RequestParam("sid") String sid) {
		
		Optional<ShoppingSession> id = sDao.findBySession(sid);
		
		if(id.isPresent()) {
			return new ResponseEntity<Integer>(id.get().getId(), HttpStatus.OK);
		}
		return new ResponseEntity<String>("Lỗi", HttpStatus.OK);
		
	}
	
	@GetMapping("/api/findss")
	public boolean getSs(@RequestParam("sid") String sid) {
		
		return sDao.findBySession(sid).isPresent();
		
	}


}
