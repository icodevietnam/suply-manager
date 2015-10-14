package com.icoding.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icoding.dao.RoleDao;
import com.icoding.dao.UserDao;
import com.icoding.domain.Role;

@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		com.icoding.domain.User user = userDao.getUser(username);

		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		return (UserDetails) new User(user.getUsername(), user.getPassword(),
				enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, getAuthorities(user.getRole().getId()));
	}

	public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
		return authList;
	}

	public List<String> getRoles(Integer roleId) {
		Role role = roleDao.getRole(roleId);
		List<String> roles = new ArrayList<String>();
		if (role.getName().equalsIgnoreCase("admin")) {
			roles.add("ROLE_ADMIN");
		} else if (role.getName().equalsIgnoreCase("moderator")) {
			roles.add("ROLE_MODERATOR");
		} else {
			roles.add("ROLE_USER");
		}
		return roles;
	}

	public static List<GrantedAuthority> getGrantedAuthorities(
			List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

}
