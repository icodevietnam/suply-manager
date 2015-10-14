package com.icoding.service;

import java.util.List;

import com.icoding.domain.User;

public interface UserService {
	public User getUser(String username);

	User getUser(String username, String password);

	User getUser(int id);

	void saveOrUpdate(User user);

	void delete(User user);

	void update(User user);

	List<User> getAll();
}
