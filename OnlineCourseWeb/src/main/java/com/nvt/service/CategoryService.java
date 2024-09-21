/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nvt.service;

import com.nvt.dto.CategoryDTO;
import com.nvt.pojo.Category;
import java.util.List;

/**
 *
 * create interface to show services of category
 *
 */
public interface CategoryService {

    List<CategoryDTO> getCatesDTO();

    List<Category> getCates();

    void deleteCate(int id);

    void addOrUpCate(Category cate);

    Category getCateById(int id);
}
