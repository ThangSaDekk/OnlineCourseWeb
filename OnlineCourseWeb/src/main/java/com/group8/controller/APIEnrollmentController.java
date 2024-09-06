/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group8.controller;

import com.group8.dto.EnrollmentDTO;
import com.group8.service.EnrollmentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author TAN DAT
 */
@RestController
@RequestMapping("/api")
public class APIEnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/enrollments/{courseId}")
    public ResponseEntity<List<EnrollmentDTO>> getEnrollmentsByCourseId(@PathVariable("courseId") int courseId) {
        List<EnrollmentDTO> enrollmentDTOs = enrollmentService.getEnrollmentsByCourseId(courseId);

//        if (enrollmentDTOs == null || enrollmentDTOs.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }

        return ResponseEntity.ok(enrollmentDTOs);
    }
}
