/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nvt.service;

import com.nvt.dto.AddUserDTO;
import com.nvt.dto.UserDTO;
import com.nvt.pojo.User;
import java.util.List;
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
    
    User getUserByID(int id);

    boolean authUser(String username, String password);

    UserDTO addUser(Map<String, String> params, MultipartFile avatar);

    long countUsers(Map<String, String> params);

    void addUserInstructor(User user);

    void deleteUserInstuctor(int id);
    
    List<User> getUserList(Map<String, String> params);
    
    void addUpUser(User u);


}
