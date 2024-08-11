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
public class UserServiceImpl implements UserService {

    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO registerNewUser(UserDTO userDto) {
        User user = modelMapper.map(userDto, User.class);
        // Additional logic to handle roles can be added here if needed
        user = userRepo.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDto) {
        User existingUser = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        
        // Update fields
        existingUser.setName(userDto.getName());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setPassword(userDto.getPassword());
        existingUser.setRole(userDto.getRole());
        existingUser.setContactNumber(userDto.getContactNumber());
        existingUser.setAddress(userDto.getAddress());
       

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
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        userRepo.delete(user);
    }
}
