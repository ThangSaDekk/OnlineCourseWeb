/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group8.controller;

import com.group8.service.CategoryService;
import com.group8.service.CourseService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author thang
 */
@Controller
@ControllerAdvice
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private CategoryService categoryService;
    
    @ModelAttribute
    public void commAttrs(Model model) {
        model.addAttribute("categories", categoryService.getCates());
    }

    @RequestMapping("/courses")
    public String courseView(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("courses", this.courseService.getCourse(params));
        
        return "course";
    }
}
