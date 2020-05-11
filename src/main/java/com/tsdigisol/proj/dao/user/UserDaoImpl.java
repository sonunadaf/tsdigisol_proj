package com.tsdigisol.proj.dao.user;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tsdigisol.proj.domain.User;
import com.tsdigisol.proj.exception.DaoException;

@Repository
public class UserDaoImpl implements IUserDao {

	private static Logger logger = Logger.getLogger(UserDaoImpl.class);

	public UserDaoImpl() {
		logger.info("Created : " + this.getClass().getSimpleName());
	}

	@Autowired
	private SessionFactory sessionFactory;

	public void save(User user) throws DaoException {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(user);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			throw new DaoException(e.getMessage());
		} finally {
			session.close();
		}

	}

	public User getUserById(int id) throws DaoException {
		Session session = sessionFactory.openSession();
		try {
			return session.get(User.class, id);
		} catch (HibernateException e) {
			throw new DaoException(e.getMessage());
		} finally {
			session.close();
		}
	}

	public List<User> getAllUser() throws DaoException {
		Session session = sessionFactory.openSession();
		try {
			String sql = "from User";
			Query query = session.createQuery(sql);
			List<User> userList = query.list();
			return userList;
		} catch (HibernateException e) {
			throw new DaoException(e.getMessage());
		} finally {
			session.close();
		}
	}

	public void deleteUser(User user) throws DaoException {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {

			session.delete(user);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			throw new DaoException(e.getMessage());
		} finally {
			session.close();
		}
	}

}
