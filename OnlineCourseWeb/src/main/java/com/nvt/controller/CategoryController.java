/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvt.controller;

import com.nvt.dto.AddCourseDTO;
import com.nvt.dto.CategoryDTO;
import com.nvt.pojo.Category;
import com.nvt.service.CategoryService;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author thang
 */
@Controller
@ControllerAdvice
public class CategoryController {

    @Autowired
    CategoryService categorySer;

    @RequestMapping("/categories")
    public String courseView(Model model) {
        List<Category> cate = this.categorySer.getCates();
        model.addAttribute("categories", cate);
        return "category";
    }


    @PostMapping("/categories/add-up")
    public String courseAddOrUpdateView(Model model, @ModelAttribute(value = "categoryDTO") @Valid CategoryDTO categoryDTO,
            BindingResult rs,
            RedirectAttributes redirectAttributes) {
        if (rs.hasErrors()) {
            model.addAttribute("errors", rs.getAllErrors());
            return "add-up-category";
        }
        try{

              Category cate = new Category();
              cate.setId(categoryDTO.getId());
              cate.setName(categoryDTO.getName());
              cate.setDescription(categoryDTO.getDescription());
              if(cate.getId() != null){
                  cate.setCreatedDate(this.categorySer.getCateById(categoryDTO.getId()).getCreatedDate());
              }
              else{
                  cate.setCreatedDate(new Date());
              }
              cate.setUpdatedDate(new Date());
              this.categorySer.addOrUpCate(cate);
//
//            this.courseService.addOrUpCourse(course);
            redirectAttributes.addFlashAttribute("successMsg", "Khóa học đã được lưu thành công.");
            return "redirect:/categories";  // Chuyển hướng đến danh sách khóa học
        } catch (Exception ex) {
            return "redirect:/categories/add-up";  // Chuyển hướng lại trang thêm/sửa khóa học
        }
    }
    
    
    @GetMapping("/categories/add-up")
    public String createView(Model model) {
        model.addAttribute("categoryDTO", new Category());
        return "add-up-category";
    }
    
    @GetMapping("/categories/{id}/add-up")
    public String updatesView(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("categoryDTO", this.categorySer.getCateById(id));

        return "add-up-category";
    }
}
