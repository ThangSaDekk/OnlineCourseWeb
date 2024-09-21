package com.group8.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class PaymentDTO {
    private String paymentChannel;
    private String payerName;
    private String payerEmail;
    private String phone;
    private List<CoursePaymentDTO> courses;



    // Calculate total amount
    public long getTotalAmount() {
        return courses.stream()
                .mapToLong(c -> c.getPrice())
                .sum();
    }
    
    public String getNameCourses() {
        return courses.stream()
                .map(c -> c.getTitle()).collect(Collectors.joining(", "));
    }
}
