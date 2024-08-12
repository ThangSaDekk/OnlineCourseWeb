/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.group8.service;

import com.group8.dto.AddCourseDTO;
import com.group8.dto.CourseDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author thang
 */
public interface CourseService {
    List<CourseDTO> getCourse(Map<String, String> params);
    void addOrUpCourse(AddCourseDTO addCourseDTO);
}
