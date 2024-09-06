/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.group8.service;

import com.group8.dto.EnrollmentDTO;
import com.group8.pojo.Enrollment;
import java.util.List;
import java.util.Map;

/**
 *
 * @author thang
 */
public interface EnrollmentService {

    List<Enrollment> getEnrollments(Map<String, String> params);

    void addOrUpEnrollment(Enrollment enrollment);
    
    List<EnrollmentDTO> getEnrollmentsByCourseId(int courseId);
}
