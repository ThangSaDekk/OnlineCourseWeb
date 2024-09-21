/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nvt.repository;

import com.nvt.pojo.Assignment;
import com.nvt.pojo.Content;
import com.nvt.pojo.Information;
import com.nvt.pojo.Lecture;
import com.nvt.pojo.Video;
import java.util.List;
import java.util.Map;

/**
 *
 * @author thang
 */
public interface ContentRepository {

    List<Content> getContentByCourseId(int id, Map<String, String> params);

    Lecture getLectureById(int id);

    Video getVideoById(int id);

    Information getInformationById(int id);

    void addUpContent(Content content);

    void addUpLecture(Lecture lecture);

    void addUpVideo(Video video);

    void addUpInformation(Information infor);
    
    void addUpAssignment(Assignment assig);

    Content getContentById(int id);

    void deleteContent(int id);

    void deleteLecture(int id);

    void deleteVideo(int id);

    void deleteInformation(int id);

    Content getContentDTOByCourseIdAndContentId(int contentId);

}
