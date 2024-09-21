/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvt.service.impl;

import com.nvt.dto.CategoryDTO;
import com.nvt.pojo.Category;
import com.nvt.repository.CategoryRepository;
import com.nvt.service.CategoryService;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Implement service to get categories of courses
 *
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository cateRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoryDTO> getCatesDTO() {
        List<Category> categories = this.cateRepo.getCates();
        return categories.stream()
                .map(c -> {
                    CategoryDTO categoryDTO = new CategoryDTO();
                    modelMapper.map(c, categoryDTO);
                    return categoryDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Category> getCates() {
        return this.cateRepo.getCates();
    }

    @Override
    public void deleteCate(int id) {
        this.cateRepo.deleteCate(id);
    }

    @Override
    public void addOrUpCate(Category cate) {
        this.cateRepo.addOrUpCate(cate);
    }

    @Override
    public Category getCateById(int id) {
        return this.cateRepo.getCateById(id);
    }
    
    

}
