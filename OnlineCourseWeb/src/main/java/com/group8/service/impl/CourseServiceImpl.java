/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group8.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.group8.dto.AddCourseDTO;
import com.group8.dto.CourseDTO;
import com.group8.pojo.Course;
import com.group8.repository.CourseRepository;
import com.group8.service.CourseService;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author thang
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<CourseDTO> getCourse(Map<String, String> params) {
        List<Course> courses = this.courseRepo.getCourse(params);
        return courses.stream()
                .map(c -> {
                    CourseDTO courseDTO = new CourseDTO();
                    modelMapper.map(c, courseDTO);
                    return courseDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void addOrUpCourse(AddCourseDTO addCourseDTO) {
        if (!addCourseDTO.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(addCourseDTO.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));

                addCourseDTO.setImg(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(CourseServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Course course = new Course();
        modelMapper.map(addCourseDTO, course);
        course.setCreatedDate(new Date());
        course.setUpdatedDate(new Date());
        this.courseRepo.addOrUpCourse(course);
    }
        
}
