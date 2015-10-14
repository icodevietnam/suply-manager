package com.icoding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icoding.dao.UserDao;
import com.icoding.domain.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User getUser(String username) {
		return userDao.getUser(username);
	}

	@Override
	public User getUser(String username, String password) {
		return userDao.getUser(username, password);
	}

	@Override
	public User getUser(int id) {
		return userDao.getUser(id);
	}

	@Override
	public void saveOrUpdate(User user) {
		userDao.saveOrUpdate(user);
	}

	@Override
	public void delete(User user) {
		userDao.delete(user);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

}
