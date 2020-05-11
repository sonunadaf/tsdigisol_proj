package com.tsdigisol.proj.dao.address;

import java.util.List;

import com.tsdigisol.proj.domain.Address;
import com.tsdigisol.proj.exception.DaoException;

public interface IAddressDao {

	public void save(Address address) throws DaoException;

	public Address getById(int id) throws DaoException;

	public List<Address> getAllAddresses() throws DaoException;

	public void deleteAddress(Address address) throws DaoException;

}
