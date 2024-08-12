/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group8.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.group8.pojo.CourseStatus;
import com.group8.pojo.CourseType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author thang
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddCourseDTO {
//     private Integer id;
    private String title;
    private String description;
    private String timeExperted;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
//    private Date createdDate;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
//    private Date updatedDate;
    private long price;
    private CourseStatus status;
    private CourseType courseType;
    private CategoryDTO categoryId;
    private InstructorDTO instructorId;
    private String img;
    private MultipartFile file;
}
