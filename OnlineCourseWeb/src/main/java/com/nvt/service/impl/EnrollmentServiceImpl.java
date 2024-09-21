/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvt.service.impl;

import com.nvt.dto.CourseDTO;
import com.nvt.pojo.Course;
import com.nvt.dto.CourseEnrollmentDTO;
import com.nvt.dto.InstructorEnrollmentDTO;
import com.nvt.dto.UserDTO;
import com.nvt.pojo.Enrollment;
import com.nvt.pojo.Instructor;
import com.nvt.pojo.User;
import com.nvt.repository.CourseRepository;
import com.nvt.repository.EnrollmentRepository;
import com.nvt.repository.InstructorRepository;
import com.nvt.repository.UserRepository;
import com.nvt.service.EnrollmentService;
import java.util.ArrayList;
import java.util.Date;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
    private CourseRepository courseRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private InstructorRepository instructorRepo;

    @Override
    public List<Enrollment> getEnrollments(Map<String, String> params) {
        return this.enrollmentRepo.getEnrollments(params);
    }

    @Override
    public void addOrUpEnrollment(Enrollment enrollment) {
        this.enrollmentRepo.addOrUpEnrollment(enrollment);
    }

    @Override
    public List<InstructorEnrollmentDTO> getEnrollmentsByCourseId(int id, Principal principal) {
        int userId = 0;
        if (principal != null) {
            User u = this.userRepo.getUserByUsername(principal.getName());
            if ("ROLE_INSTRUCTOR".equals(u.getUserRole())) {
                userId = u.getId();
            }
        }

        try {
            Course course = this.courseRepo.getCourseById(id);

            Map<String, String> params = new HashMap<>();
            params.put("userId", String.valueOf(userId));
            Instructor ins = this.instructorRepo.getAllInstructors(params).get(0);
            
            if(Objects.equals(ins.getId(), course.getInstructorId()))
            {
                return null;
            }

            // Chuyển đổi PersistentSet thành List
            List<Enrollment> enrollments = new ArrayList<>(course.getEnrollmentSet());

            List<InstructorEnrollmentDTO> instructorEnrollmentDTOs = new ArrayList<>();

            for (Enrollment enrollment : enrollments) {
                InstructorEnrollmentDTO enrollmentDTO = new InstructorEnrollmentDTO();
                enrollmentDTO.setId(enrollment.getId());
                enrollmentDTO.setUserId(this.modelMapper.map(enrollment.getUserId(), UserDTO.class));
                enrollmentDTO.setCourseId(this.modelMapper.map(enrollment.getCourseId(), CourseDTO.class));
                enrollmentDTO.setStatus(enrollment.getStatus().name());
                enrollmentDTO.setProgress(enrollment.getProgress());
                enrollmentDTO.setCreatedDate(new Date());
                enrollmentDTO.setUpdatedDate(new Date());
                instructorEnrollmentDTOs.add(enrollmentDTO);
            }
            return instructorEnrollmentDTOs;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public CourseEnrollmentDTO getEnrollmentByCourseIdAndUserId(int courseId, Principal principal) {
        int userId = 0;
        if (principal != null) {
            userId = this.userRepo.getUserByUsername(principal.getName()).getId();
        }
        Enrollment enrollment = this.enrollmentRepo.getEnrollmentByCourseIdAndUserId(courseId, userId);
        CourseEnrollmentDTO dto = modelMapper.map(enrollment, CourseEnrollmentDTO.class);
        return dto;

    }

}
