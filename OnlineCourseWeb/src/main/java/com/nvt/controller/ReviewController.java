/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvt.controller;

import com.nvt.pojo.Review;
import com.nvt.service.ReviewService;
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
public class ReviewController {
    
    @Autowired
    private Environment env;
    
    @Autowired
    private ReviewService reviewSer;
    
    @RequestMapping("/reviews")
    public String creatView(@RequestParam Map<String, String> params, Model model){
        if (params.get("page") == null) {
            params.put("page", "1");
        }
        List<Review> reviews = this.reviewSer.getReviewList(params);

        int total = this.reviewSer.getReviewList(null).size();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (!entry.getKey().equals("page") && entry.getValue() != null && !entry.getValue().isEmpty()) {
                params.remove("page");
                total = this.reviewSer.getReviewList(params).size();
                break;
            }
        }
        int PAGE_MAX = Integer.parseInt(env.getProperty("page.size.review"));

        int pageTotal = (int) Math.ceil((double) total / PAGE_MAX);

        model.addAttribute("reviews", reviews);
        model.addAttribute("pageTotal", pageTotal); // gửi biến tổng trang để phân trang
        return "review";
    }
}
