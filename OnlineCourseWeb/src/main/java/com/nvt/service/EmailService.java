/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvt.service;

import com.nvt.pojo.User;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author thang
 */
public interface EmailService {
    void sendEnrollmentSuccessMessage(User u, HttpServletRequest request, String toMail);
}
