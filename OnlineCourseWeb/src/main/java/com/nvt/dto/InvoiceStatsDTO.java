/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvt.dto;

import com.nvt.pojo.Course;
import lombok.Data;

/**
 *
 * @author TAN DAT
 */
@Data
public class InvoiceStatsDTO {
    private Integer id;
    private double totalAmount;
    private Course courseId;
    private long price;
}
