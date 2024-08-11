package com.app.service;

import java.util.List;

import com.app.dto.UserDTO;
import com.app.entity.User;

public interface IUserService {
	
	User createUser(UserDTO userDto);

	User updateUser(Long userId, UserDTO userDto);

	User getUserById(Long userId);

	List<UserDTO> getAllUsers();

	String deleteUser(Long userId);

}
