package com.tsdigisol.proj.dao.user;

import java.util.List;

import com.tsdigisol.proj.domain.User;
import com.tsdigisol.proj.exception.DaoException;

public interface IUserDao {

	public void save(User user) throws DaoException;

	public User getUserById(int id) throws DaoException;

	public List<User> getAllUser() throws DaoException;

	public void deleteUser(User user)throws DaoException;

}
