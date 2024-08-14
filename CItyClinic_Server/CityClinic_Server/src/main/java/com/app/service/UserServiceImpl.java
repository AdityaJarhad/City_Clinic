package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dto.UserDTO;
import com.app.entity.Patient;
import com.app.entity.Role;
import com.app.entity.User;
import com.app.repository.IPatientRepository;
import com.app.repository.IUserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private IUserRepository userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IPatientRepository patientRepo;

	@Autowired
	private PasswordEncoder encoder; // Injecting PasswordEncoder

	@Override
	public UserDTO registerNewUser(UserDTO userDto) {
		// Check if email already exists
		if (userRepo.existsByEmail(userDto.getEmail())) {
			throw new IllegalArgumentException("Email already exists !!!");
		}

		// Map the UserDTO to a User entity
		User user = modelMapper.map(userDto, User.class);

		// Encode the password before saving
		user.setPassword(encoder.encode(user.getPassword()));

		// Save the user to the database
		User savedUser = userRepo.save(user);

		// If the role is 'Patient', create a corresponding Patient record
		if (user.getRole() == Role.PATIENT) {
			Patient patient = new Patient();
			patient.setUser(savedUser);
			patientRepo.save(patient);
		}

		// Return the saved user as a DTO
		return modelMapper.map(savedUser, UserDTO.class);
	}

	@Override
	public UserDTO updateUser(Long userId, UserDTO userDto) {
		User existingUser = userRepo.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

		// Check if the provided password matches the existing user's password
	    if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
	        if (!encoder.matches(userDto.getPassword(), existingUser.getPassword())) {
	            throw new IllegalArgumentException("Incorrect password. Update failed.");
	        }
	    }
		
		// Update fields
		existingUser.setName(userDto.getName());
		existingUser.setEmail(userDto.getEmail());
		existingUser.setRole(userDto.getRole());
		existingUser.setContactNumber(userDto.getContactNumber());
		existingUser.setAddress(userDto.getAddress());

		// Update the password only if it's provided
		if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
			existingUser.setPassword(encoder.encode(userDto.getPassword()));
		}

		User updatedUser = userRepo.save(existingUser);
		return modelMapper.map(updatedUser, UserDTO.class);
	}

	@Override
	public UserDTO getUserById(Long userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
		return modelMapper.map(user, UserDTO.class);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = userRepo.findAll();
		return users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
	}

	@Override
	public void deleteUser(Long userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
		userRepo.delete(user);
	}
}