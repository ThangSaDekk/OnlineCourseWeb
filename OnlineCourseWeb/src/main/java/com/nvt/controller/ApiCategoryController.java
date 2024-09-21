/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvt.controller;

import com.nvt.dto.CategoryDTO;
import com.nvt.service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * create API to fetch categories /api/categories/
 *
 */
@Controller
@RequestMapping("/api")
@CrossOrigin
public class ApiCategoryController {

    @Autowired
    private CategoryService cateService;

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> list() {
        return new ResponseEntity<>(this.cateService.getCatesDTO(), HttpStatus.OK);
    }

    @DeleteMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") int id) {
        this.cateService.deleteCate(id);
    }
}
