/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.group8.service;

import com.group8.dto.AddContentDTO;
import com.group8.dto.ContentDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author thang
 */
public interface ContentService {

    public List<ContentDTO> getContentDTOsByCourseId(int id, Map<String, String> params);

    void addUpContent(AddContentDTO addContentDTO);

    AddContentDTO getContentById(int id);

    public String removeLastCharacter(String str);

    void deleteContent(int id);

}
