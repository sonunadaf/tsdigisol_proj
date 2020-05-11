package com.tsdigisol.proj.service.address;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsdigisol.proj.controller.address.dto.AddressDTO;
import com.tsdigisol.proj.dao.address.IAddressDao;
import com.tsdigisol.proj.dao.user.IUserDao;
import com.tsdigisol.proj.domain.Address;
import com.tsdigisol.proj.domain.User;
import com.tsdigisol.proj.exception.DaoException;
import com.tsdigisol.proj.exception.ServiceException;

@Service
public class AddressServiceImpl implements IAddressService {

	@Autowired
	private IAddressDao addressDao;
	@Autowired
	private IUserDao userDao;
	private static Logger logger = Logger.getLogger(AddressServiceImpl.class);

	public AddressServiceImpl() {
		logger.info("Created : " + this.getClass().getSimpleName());
	}

	public void createAddress(AddressDTO addressDTO) throws ServiceException {
		try {
			User user = userDao.getUserById(addressDTO.getUserId());
			if (user == null) {
				throw new ServiceException("Invalid user id.");
			}
			Address address = new Address();
			BeanUtils.copyProperties(addressDTO, address);
			address.setUser(user);
			addressDao.save(address);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	public void update(AddressDTO addressDTO) throws ServiceException {
		try {
			Address address = addressDao.getById(addressDTO.getId());
			if (address == null) {
				throw new ServiceException("Invalid id.");
			}
			address.setCity(addressDTO.getCity());
			address.setHouseNumber(addressDTO.getHouseNumber());
			address.setPincode(addressDTO.getPincode());
			addressDao.save(address);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	public AddressDTO getById(int id) throws ServiceException {
		try {
			Address address = addressDao.getById(id);
			if (address == null) {
				throw new ServiceException("Invalid id.");
			}
			AddressDTO addressDTO = new AddressDTO();
			BeanUtils.copyProperties(address, addressDTO);
			return addressDTO;
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<AddressDTO> getAllAddress() throws ServiceException {
		try {
			List<Address> addresses = addressDao.getAllAddresses();
			List<AddressDTO> addressDTOs = new ArrayList<AddressDTO>();
			for (Address address : addresses) {
				AddressDTO addressDTO = new AddressDTO();
				BeanUtils.copyProperties(address, addressDTO);
				addressDTOs.add(addressDTO);
			}
			return addressDTOs;
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void deteteAddress(int id) throws ServiceException {
		try {
			Address address = addressDao.getById(id);
			if (address == null) {
				throw new ServiceException("Invalid id.");
			}
			addressDao.deleteAddress(address);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
