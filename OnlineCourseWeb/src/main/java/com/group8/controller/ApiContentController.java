/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group8.controller;

import com.group8.dto.ContentDTO;
import com.group8.service.ContentService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author thang
 */
@Controller
@RequestMapping("/api")
@CrossOrigin
public class ApiContentController {
    
    @Autowired
    private ContentService contentService;
    
    @GetMapping("/courses/{courseId}/content")
    public ResponseEntity<List<ContentDTO>> creatViewContent(Model model, @PathVariable(value = "courseId") int id, Map<String, String> params ){
        return new ResponseEntity<>(this.contentService.getContentDTOsByCourseId(id, params), HttpStatus.OK);
    }
    
    
    @DeleteMapping("/courses/{courseId}/content/{contentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "courseId") int id, @PathVariable(value = "contentId") int contentId) {
        this.contentService.deleteContent(contentId);
    }
            

}
