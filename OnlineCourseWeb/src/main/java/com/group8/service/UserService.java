/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.group8.service;

import com.group8.dto.AddUserDTO;
import com.group8.dto.UserDTO;
import com.group8.pojo.User;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author admin
 */
public interface UserService extends UserDetailsService {

    User getUserByUsername(String username);

    AddUserDTO getUserById(Integer id);

    UserDTO getUserDTO(String username);

    boolean authUser(String username, String password);

    UserDTO addUser(Map<String, String> params, MultipartFile avatar);

    long countUsers(Map<String, String> params);

    void addUserInstructor(User user);

    void deleteUserInstuctor(int id);

    void changePassword(User user);

}
