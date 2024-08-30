/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group8.service.impl;

import com.group8.pojo.Invoice;
import com.group8.repository.InvoiceRepository;
import com.group8.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author thang
 */
@Service
public class InvoiceServiceImpl implements InvoiceService{
    
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public Invoice checkrequestId(String RequestId) {
        return this.invoiceRepository.checkrequestId(RequestId);
    }

    @Override
    public void addUpInvoice(Invoice invoice) {
        this.invoiceRepository.addUpInvoice(invoice);
    }

   
    
    
    
}
