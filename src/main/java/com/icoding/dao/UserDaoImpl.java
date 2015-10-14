package com.icoding.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icoding.domain.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUser(String username) {
		List<User> userList = new ArrayList<User>();
		Query query = getCurrentSession().createQuery(
				"from User u where u.username = :username");
		query.setParameter("username", username);
		userList = query.list();
		if (userList.size() > 0)
			return userList.get(0);
		else
			return null;
	}

	@Override
	public User getUser(String username, String password) {
		List<User> userList = new ArrayList<User>();
		Query query = getCurrentSession()
				.createQuery(
						"from User u where u.username = :username and u.password = :password");
		query.setParameter("username", username);
		query.setParameter("password", password);
		userList = query.list();
		if (userList.size() > 0)
			return userList.get(0);
		else
			return null;
	}

	// Get User By Id
	@Override
	public User getUser(int id) {
		List<User> userList = new ArrayList<User>();
		Query query = getCurrentSession().createQuery(
				"from User u where u.id = :id");
		query.setParameter("id", id);
		userList = query.list();
		if (userList.size() > 0)
			return userList.get(0);
		else
			return null;
	}

	@Override
	public void saveOrUpdate(User user) {
		try {
			getCurrentSession().saveOrUpdate(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(User user) {
		getCurrentSession().delete(user);
	}

	@Override
	public void update(User user) {
		getCurrentSession().update(user);
	}

	@Override
	public List<User> getAll() {

		List<User> userList = new ArrayList<User>();
		userList = getCurrentSession().createCriteria(User.class).list();
		return userList;
	}

}
