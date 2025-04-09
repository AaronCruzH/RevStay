package com.revature.stay.services;


import com.revature.stay.models.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    List<User> getUsersByRole(String role);
    User updateUser(Long id, User userDetails);
    void deleteUser(Long id);
    boolean existsByEmail(String email);
}
