/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group8.repository;

import com.group8.pojo.Invoice;

/**
 *
 * @author thang
 */
public interface InvoiceRepository {
    void addUpInvoice(Invoice invoice);
    
    Invoice checkrequestId(String RequestId);
}
