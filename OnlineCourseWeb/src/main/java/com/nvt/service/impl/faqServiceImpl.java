/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvt.service.impl;

import com.nvt.pojo.Faq;
import com.nvt.repository.faqRepository;
import com.nvt.service.faqService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author thang
 */
@Service
public class faqServiceImpl implements faqService{
    
    @Autowired
    private faqRepository faqRepo;

    @Override
    public List<Faq> getFaqList(Map<String, String> params) {
        return this.faqRepo.getFaqList(params);
    }
    
}
