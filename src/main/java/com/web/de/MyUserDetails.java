package com.web.de;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.web.user.User;

public class MyUserDetails implements UserDetails {

	private String userName;
	private String password;
	private boolean active;
	private User u;
	private List<GrantedAuthority> authorities;

	public MyUserDetails(User user) {
		this.u = user;
		this.userName = user.getEmail();
				
		this.password = user.getPasswords();
		this.active = user.getActived();
		this.authorities = Arrays.stream(user.getRole().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public String getPhone() {
		return this.u.getPhone();
	}
	
	public User getUser() {
		return this.u;
	}
	
	public String toString() {
		return "LogedIn: " + getUser();
 	}
	
	public String getName() {
		return getUser().getName();
	}
	
	public Integer getId() {
		return getUser().getId();
	}

}
