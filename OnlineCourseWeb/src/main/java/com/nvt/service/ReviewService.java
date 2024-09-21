/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvt.service;

import com.nvt.pojo.Review;
import java.util.List;
import java.util.Map;

/**
 *
 * @author thang
 */
public interface ReviewService {
       List<Review> getReviewList(Map<String,String> params); 
}
