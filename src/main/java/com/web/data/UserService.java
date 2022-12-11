package com.web.data;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.user.User;
import com.web.user.UserNotFoundException;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepo;

	public List<User> listAllUser() {
		return userRepo.findAll();
	}

	public void save(User u) {
		userRepo.save(u);
	}

	public User get(Integer id) {
		return userRepo.findById(id).get();
	}

	public void delete(Integer id) {
		userRepo.deleteById(id);
	}

	public void updateResetPasswordToken(String token, String email) throws UserNotFoundException {
		User u = userRepo.findByEmail1(email);

		if (u != null) {
			u.setResetPasswordToken(token);
			userRepo.save(u);
		} else {
			throw new UserNotFoundException("Could not find any user with email " + email);
		}
	}

	public User get(String resetPasswordToken) {
		return userRepo.findByResetPasswordToken(resetPasswordToken);
	}

	public void updatePassword(User u, String newPassword) {
		// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		//
		// String encodedPassword = passwordEncoder.encode(newPassword);

		u.setPasswords(newPassword);
		u.setResetPasswordToken(null);
		userRepo.save(u);
	}

}
