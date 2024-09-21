package com.nvt.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AddCourseDTO {
    private Integer id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Time expected is required")
    private String timeExperted;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Long price;

    @NotBlank(message = "Status is required")
    private String status;

    @NotBlank(message = "Course type is required")
    private String courseType;

    @NotNull(message = "Category ID is required")
    private Integer categoryId;

    @NotNull(message = "Instructor ID is required")
    private Integer instructorId;

    private MultipartFile file;
    private String img;
}
