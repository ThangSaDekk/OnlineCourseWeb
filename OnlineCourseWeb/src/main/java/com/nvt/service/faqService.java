/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nvt.service;

import com.nvt.pojo.Faq;
import java.util.List;
import java.util.Map;

/**
 *
 * @author thang
 */
public interface faqService {

    List<Faq> getFaqList(Map<String, String> params);

}
