package com.tsdigisol.proj.service.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsdigisol.proj.controller.user.dto.UserDTO;
import com.tsdigisol.proj.dao.user.IUserDao;
import com.tsdigisol.proj.domain.User;
import com.tsdigisol.proj.exception.DaoException;
import com.tsdigisol.proj.exception.ServiceException;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	private static Logger logger = Logger.getLogger(UserServiceImpl.class);

	public UserServiceImpl() {
		logger.info("Created : " + this.getClass().getSimpleName());
	}

	public void createUser(UserDTO userDTO) throws ServiceException {

		try {
			User user = new User();
			BeanUtils.copyProperties(userDTO, user);
			userDao.save(user);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	public void updateUser(UserDTO userDTO) throws ServiceException {
		try {
			User user = userDao.getUserById(userDTO.getId());
			if (user == null) {
				throw new ServiceException("Invalid id.");
			}
			user.setName(userDTO.getName());
			user.setEmail(userDTO.getEmail());
			user.setContacts(userDTO.getContacts());
			userDao.save(user);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public UserDTO getUserById(int id) throws ServiceException {
		try {
			User user = userDao.getUserById(id);
			if (user == null) {
				throw new ServiceException("Invalid id.");
			}
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(user, userDTO);
			return userDTO;
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<UserDTO> getAllUser() throws ServiceException {
		try {
			List<User> users = userDao.getAllUser();
			List<UserDTO> userDTOs = new ArrayList<UserDTO>();
			for (User user : users) {
				UserDTO dto = new UserDTO();
				BeanUtils.copyProperties(user, dto);
				userDTOs.add(dto);
			}
			return userDTOs;
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void delete(int id) throws ServiceException {
		try {
			User user = userDao.getUserById(id);
			if (user == null) {
				throw new ServiceException("Invalid id.");
			}
			userDao.deleteUser(user);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
