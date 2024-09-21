package com.nvt.dto;

import java.util.Map;
import lombok.Data;

@Data
public class AddContentDTO {

    private Integer id;
    private String title;
    private Integer courseId;
    private Map<String,String> content;  
    private String entityType;
    private Boolean status;

}
