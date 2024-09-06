/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group8.service.impl;

import com.group8.dto.CourseEnrollmentDTO;
import com.group8.pojo.Enrollment;
import com.group8.repository.EnrollmentRepository;
import com.group8.repository.UserRepository;
import com.group8.service.EnrollmentService;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.modelmapper.ModelMapper;
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
    private UserRepository userRepo;
    
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Enrollment> getEnrollments(Map<String, String> params) {
        return this.enrollmentRepo.getEnrollments(params);
    }

    @Override
    public void addOrUpEnrollment(Enrollment enrollment) {
        this.enrollmentRepo.addOrUpEnrollment(enrollment);
    }

    @Override
    public CourseEnrollmentDTO getEnrollmentByCourseIdAndUserId(int courseId, Principal principal) {
        int userId = 0;
        if(principal != null){
            userId = this.userRepo.getUserByUsername(principal.getName()).getId();
        }
        Enrollment enrollment = this.enrollmentRepo.getEnrollmentByCourseIdAndUserId(courseId, userId);
        CourseEnrollmentDTO dto = modelMapper.map(enrollment, CourseEnrollmentDTO.class);
        return dto;
        
    }

    

}
