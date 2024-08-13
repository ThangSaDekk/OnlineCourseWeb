/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group8.controller;

import com.group8.dto.AddCourseDTO;
import com.group8.dto.CourseDTO;
import com.group8.service.CategoryService;
import com.group8.service.CourseService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    
    @RequestMapping("/courses/add-up")
    public String courseAddOrUpdateView(Model model, @ModelAttribute(value="course") @Valid AddCourseDTO c, BindingResult rs) {
        if(rs.hasErrors())
            return "add-up-course";
        try{
            this.courseService.addOrUpCourse(c);
        }catch (Exception ex){
            model.addAttribute("errMsg", ex.getMessage());
        }
        
        return "add-up-course";
    }
}
