/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group8.service.impl;

import com.group8.dto.CourseDTO;
import com.group8.dto.EnrollmentDTO;
import com.group8.pojo.Course;
import com.group8.pojo.Enrollment;
import com.group8.pojo.Invoice;
import com.group8.repository.CourseRepository;
import com.group8.repository.EnrollmentRepository;
import com.group8.service.EnrollmentService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author thang
 */
@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepo;
    
    @Autowired
    private CourseRepository courseRepo;

    @Override
    public List<Enrollment> getEnrollments(Map<String, String> params) {
        return this.enrollmentRepo.getEnrollments(params);
    }

    @Override
    public void addOrUpEnrollment(Enrollment enrollment) {
        this.enrollmentRepo.addOrUpEnrollment(enrollment);
    }

    @Override
    public List<EnrollmentDTO> getEnrollmentsByCourseId(int id) {
        Course course = this.courseRepo.getCourseById(id);

        // Chuyển đổi PersistentSet thành List
        List<Enrollment> enrollments = new ArrayList<>(course.getEnrollmentSet());

        List<EnrollmentDTO> enrollmentDTOs = new ArrayList<>();
        
        for (Enrollment enrollment : enrollments) {
            EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
            enrollmentDTO.setId(enrollment.getId());            
//            enrollmentDTO.setUserId(enrollment.getUserId());
//            enrollmentDTO.setCourseId(enrollment.getCourseId());
            enrollmentDTO.setFirstName(enrollment.getUserId().getFirstName());
            enrollmentDTO.setLastName(enrollment.getUserId().getLastName());
            enrollmentDTO.setProgress(enrollment.getProgress());
            enrollmentDTO.setCreatedDate(new Date());
            enrollmentDTO.setUpdatedDate(new Date());
            enrollmentDTOs.add(enrollmentDTO);
        }
        return enrollmentDTOs;
    }

    

}
