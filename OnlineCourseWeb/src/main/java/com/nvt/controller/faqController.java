/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvt.controller;

import com.nvt.pojo.Faq;
import com.nvt.service.faqService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author thang
 */
@Controller
@ControllerAdvice
public class faqController {
    
    @Autowired
    private Environment env;
    
    @Autowired
    private faqService faqSer;
    
    @RequestMapping("/faqs")
    public String createView(Model model, @RequestParam Map<String,String> params){
         if (params.get("page") == null) {
            params.put("page", "1");
        }
        List<Faq> faqs = this.faqSer.getFaqList(params);

        int total = this.faqSer.getFaqList(null).size();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (!entry.getKey().equals("page") && entry.getValue() != null && !entry.getValue().isEmpty()) {
                params.remove("page");
                total = this.faqSer.getFaqList(params).size();
                break;
            }
        }
        int PAGE_MAX = Integer.parseInt(env.getProperty("page.size.faq"));

        int pageTotal = (int) Math.ceil((double) total / PAGE_MAX);
       
        model.addAttribute("faqs", faqs);
        model.addAttribute("pageTotal", pageTotal); // gửi biến tổng trang để phân trang

        return "faq";
    }
//     @PostMapping("/categories/add-up")
//    public String courseAddOrUpdateView(Model model, @ModelAttribute(value = "categoryDTO") @Valid CategoryDTO categoryDTO,
//            BindingResult rs,
//            RedirectAttributes redirectAttributes) {
//        if (rs.hasErrors()) {
//            model.addAttribute("errors", rs.getAllErrors());
//            return "add-up-category";
//        }
//        try{
//
//              Category cate = new Category();
//              cate.setId(categoryDTO.getId());
//              cate.setName(categoryDTO.getName());
//              cate.setDescription(categoryDTO.getDescription());
//              if(cate.getId() != null){
//                  cate.setCreatedDate(this.categorySer.getCateById(categoryDTO.getId()).getCreatedDate());
//              }
//              else{
//                  cate.setCreatedDate(new Date());
//              }
//              cate.setUpdatedDate(new Date());
//              this.categorySer.addOrUpCate(cate);
////
////            this.courseService.addOrUpCourse(course);
//            redirectAttributes.addFlashAttribute("successMsg", "Khóa học đã được lưu thành công.");
//            return "redirect:/categories";  // Chuyển hướng đến danh sách khóa học
//        } catch (Exception ex) {
//            return "redirect:/categories/add-up";  // Chuyển hướng lại trang thêm/sửa khóa học
//        }
//    }
//    
//    
//    @GetMapping("/categories/add-up")
//    public String createView(Model model) {
//        model.addAttribute("categoryDTO", new Category());
//        return "add-up-category";
//    }
//    
//    @GetMapping("/categories/{id}/add-up")
//    public String updatesView(Model model, @PathVariable(value = "id") int id) {
//        model.addAttribute("categoryDTO", this.categorySer.getCateById(id));
//
//        return "add-up-category";
//    }
}
