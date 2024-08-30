/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group8.service;

import com.group8.pojo.Invoice;

/**
 *
 * @author thang
 */
public interface InvoiceService {

    Invoice checkrequestId(String RequestId);
    
    void addUpInvoice(Invoice invoice);
}
