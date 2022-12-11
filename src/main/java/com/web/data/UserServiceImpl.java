package com.web.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.user.User;

@Service
public class UserServiceImpl{

//	private UserRepository userRepository;
//	
//	@Autowired
//	public UserServiceImpl(UserRepository userRepository) {
//		UserRepository employeeRepository = userRepository;
//	}
//	
//	@Override
//	public List<User> findAll() {
//		return userRepository.findAll();
//	}
//
////	@Override
////	public User findByEmail(String email) {
////		List<User> result = userRepository.findByEmail(email);
////		
////		if(result == null || result.isEmpty()) return null;
////		
////		User user = null;
////		
////		if (result == null || result.isEmpty()) {
////			throw new RuntimeException("Không tìm thấy user với email - " + email);
////		}
////		
////		
////		return 	user = result.get(0);
////	}
//
//	@Override
//	public void save(User user) {
//		userRepository.save(user);
//	}
//
//	@Override
//	public void deleteById(int theId) {
//		userRepository.deleteById(theId);
//	}
//
//	@Override
//	public User findByEmail(String email) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}






