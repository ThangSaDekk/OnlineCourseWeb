/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nvt.repository;

import com.nvt.pojo.Category;
import java.util.List;

/**
 *
 * @author thang
 */
public interface CategoryRepository {
    List<Category> getCates();
    
    void deleteCate(int id);
    
    void addOrUpCate(Category cate);
    
    Category getCateById(int id);
}
