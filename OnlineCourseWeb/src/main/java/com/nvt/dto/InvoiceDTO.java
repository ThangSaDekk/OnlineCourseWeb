/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvt.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nvt.pojo.Enum.PaymentStatus;
import java.util.Date;
import lombok.Data;

@Data
public class InvoiceDTO {

 
    private Integer id;
    private String referenceCode;
    private String payerName;
    private String payerPhone;
    private String payerEmail;
//    private String username;
//    private String password;
    private long totalAmount;
    private PaymentStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedDate;
    private Integer userId;
    private Integer courseId;    

   
}
