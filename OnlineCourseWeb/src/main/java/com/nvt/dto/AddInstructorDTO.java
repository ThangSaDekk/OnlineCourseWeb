/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvt.dto;

import lombok.Data;

/**
 *
 * @author TAN DAT
 */
@Data
public class AddInstructorDTO {
    
    
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String username;
    private String password;
//    private Boolean active;
    private String userRole;
    private String avatar;
    
    private String expertise;
    private String description;
    
}
