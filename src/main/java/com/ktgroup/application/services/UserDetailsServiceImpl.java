package com.ktgroup.application.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ktgroup.application.entities.AppUser;
import com.ktgroup.application.responsitories.AppUserRespository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AppUserRespository appUserRespository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		AppUser appUser = this.appUserRespository.findByUserName(userName);

		if (appUser == null) {
			throw new UsernameNotFoundException("User " + userName + " was not found in the database");
		}

		// [ROLE_USER, ROLE_ADMIN,..]
		String roleName = appUser.getRole().getRoleName();
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (roleName != null) {
			// ROLE_USER, ROLE_ADMIN,..
			GrantedAuthority authority = new SimpleGrantedAuthority(roleName);
			grantList.add(authority);
		}

		UserDetails userDetails = (UserDetails) new User(appUser.getUserName(), appUser.getEncrytedPassword(), grantList);
		return userDetails;
	}

}
