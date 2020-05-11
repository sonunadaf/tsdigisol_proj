package com.tsdigisol.proj.service.user;

import java.util.List;

import com.tsdigisol.proj.controller.user.dto.UserDTO;
import com.tsdigisol.proj.exception.ServiceException;

public interface IUserService {

	public void createUser(UserDTO userDTO) throws ServiceException;

	public void updateUser(UserDTO userDTO) throws ServiceException;

	public UserDTO getUserById(int id) throws ServiceException;

	public List<UserDTO> getAllUser() throws ServiceException;

	public void delete(int id) throws ServiceException;
}
