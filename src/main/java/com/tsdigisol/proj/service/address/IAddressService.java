package com.tsdigisol.proj.service.address;

import java.util.List;

import com.tsdigisol.proj.controller.address.dto.AddressDTO;
import com.tsdigisol.proj.exception.ServiceException;

public interface IAddressService {

	public void createAddress(AddressDTO addressDTO) throws ServiceException;

	public void update(AddressDTO addressDTO) throws ServiceException;

	public AddressDTO getById(int id) throws ServiceException;

	public List<AddressDTO> getAllAddress() throws ServiceException;

	public void deteteAddress(int id) throws ServiceException;
}
