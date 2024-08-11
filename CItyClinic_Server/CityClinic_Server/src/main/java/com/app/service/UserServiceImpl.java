package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.UserDTO;
import com.app.entity.User;
import com.app.repository.IUserRepository;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public User createUser(UserDTO userDto) {
		User user = mapper.map(userDto, User.class);
		return userRepo.save(user);
	}

	@Override
	public User updateUser(Long userId, UserDTO userDto) {
		User user = userRepo.findById(userId).orElseThrow();
		mapper.map(userDto, user);
		return userRepo.save(user);
	}

	@Override
	public User getUserById(Long userId) {
		return userRepo.findById(userId).orElseThrow();
	}

	@Override
	public List<UserDTO> getAllUsers() {
		return userRepo.findAll().stream().map(user -> mapper.map(user, UserDTO.class)).collect(Collectors.toList());
	}

	@Override
	public String deleteUser(Long userId) {
		User user = userRepo.findById(userId).orElseThrow();
		userRepo.delete(user);
		return "User deleted: " + user.getName();
	}
}
