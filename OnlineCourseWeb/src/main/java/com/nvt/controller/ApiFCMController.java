/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvt.controller;

import com.nvt.service.FCMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author thang
 */
@Controller
@RequestMapping("/api")
@CrossOrigin
public class ApiFCMController {

    @Autowired
    private FCMService fcmService;

    @GetMapping("/access-token")
    public ResponseEntity<String> getAccessToken() {

        return new ResponseEntity<>(this.fcmService.getAccessToken(), HttpStatus.OK);
    }
}
