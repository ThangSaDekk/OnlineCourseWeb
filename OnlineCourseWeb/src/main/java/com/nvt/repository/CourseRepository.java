/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nvt.repository;

import com.nvt.pojo.Course;
import java.util.List;
import java.util.Map;

/**
 *
 * @author thang
 */
public interface CourseRepository {

    List<Course> getCourse(Map<String, String> params);

    void addOrUpCourse(Course course);

    Course getCourseById(int id);

    void deleteCourse(int id);
    
    String getOrderInfor(String stringArray);
    
    List<Course> getCourseDTOByInstructorId(int instructorId);
    

}
