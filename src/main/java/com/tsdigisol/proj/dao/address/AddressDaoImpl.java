package com.tsdigisol.proj.dao.address;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tsdigisol.proj.domain.Address;
import com.tsdigisol.proj.exception.DaoException;

@Repository
public class AddressDaoImpl implements IAddressDao {

	@Autowired
	private SessionFactory sessionFactory;
	private static Logger logger = Logger.getLogger(AddressDaoImpl.class);

	public AddressDaoImpl() {
		logger.info("Created : " + this.getClass().getSimpleName());
	}

	public void save(Address address) throws DaoException {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(address);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			throw new DaoException(e.getMessage());
		} finally {
			session.close();
		}

	}

	public Address getById(int id) throws DaoException {
		Session session = sessionFactory.openSession();
		try {
			Address address = session.get(Address.class, id);
			return address;
		} catch (HibernateException e) {
			throw new DaoException(e.getMessage());
		} finally {
			session.close();
		}
	}

	public List<Address> getAllAddresses() throws DaoException {
		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery("from Address");
			return query.list();
		} catch (HibernateException e) {
			throw new DaoException(e.getMessage());
		} finally {
			session.close();
		}
	}

	public void deleteAddress(Address address) throws DaoException {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(address);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			throw new DaoException(e.getMessage());
		} finally {
			session.close();
		}
	}
}
