/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nvt.service;

import com.nvt.dto.AddContentDTO;
import com.nvt.dto.ContentDTO;
import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author thang
 */
public interface ContentService {

    public List<ContentDTO> getContentDTOsByCourseId(int id, Map<String, String> params, Principal principal);

    void addUpContent(AddContentDTO addContentDTO);

    AddContentDTO getContentById(int id);

    public String removeLastCharacter(String str);

    void deleteContent(int id);
    
    ContentDTO getContentDTOsByCourseIdAndContentId(int id, int contentId, Principal principal);
    
    boolean handleMarkCompleted(int courseId, int contentId, Principal principal, Map<String, String> processData);

}
