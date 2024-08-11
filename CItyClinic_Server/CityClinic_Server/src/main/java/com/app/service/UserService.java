package com.app.service;

import com.app.dto.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO registerNewUser(UserDTO userDto);
    UserDTO updateUser(Long userId, UserDTO userDto);
    UserDTO getUserById(Long userId);
    List<UserDTO> getAllUsers();
    void deleteUser(Long userId);
}
