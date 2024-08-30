/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.group8.service;

import com.group8.dto.EnrollmentDTO;
import com.group8.dto.InvoiceDTO;

import com.group8.pojo.Invoice;
import java.util.List;
import java.util.Map;

/**
 *
 * @author TAN DAT
 */
public interface InvoiceService {
    
    List<Invoice> getAllInvoice(Map<String,String>params);
    List<EnrollmentDTO> getInvoiceById(int id);
    
}
