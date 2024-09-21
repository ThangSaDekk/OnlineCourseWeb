/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvt.service;

import com.nvt.pojo.Invoice;
import java.util.Map;
import java.util.List;
import com.nvt.dto.EnrollmentDTO;
import com.nvt.dto.InvoiceStatsDTO;

/**
 *
 * @author thang
 * @author TAN DAT
 */
public interface InvoiceService {

    Invoice checkrequestId(String RequestId);

    void addUpInvoice(Invoice invoice);

    List<Invoice> getAllInvoice(Map<String, String> params);

    List<EnrollmentDTO> getInvoiceById(int id);

    List<InvoiceStatsDTO> calculateTotalAmount(int id);
}
