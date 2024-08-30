/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group8.service.impl;

import com.group8.dto.AddContentDTO;
import com.group8.dto.ContentDTO;
import com.group8.pojo.Content;
import com.group8.pojo.Enum.EntityType;
import com.group8.pojo.Information;
import com.group8.pojo.Lecture;
import com.group8.pojo.Video;
import com.group8.repository.ContentRepository;
import com.group8.repository.CourseRepository;
import com.group8.service.ContentService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author thang
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentRepository contentRepo;

    @Autowired
    private CourseRepository courseRepo;

    @Override
    public List<ContentDTO> getContentDTOsByCourseId(int id, Map<String, String> params) {
        List<Content> contents = this.contentRepo.getContentByCourseId(id, params);
        System.out.println(contents);
        List<ContentDTO> contentDTOs = new ArrayList<>();

        for (Content content : contents) {
            ContentDTO dto = ContentDTO.fromContent(content, contentRepo);
            contentDTOs.add(dto);
        }

        return contentDTOs;
    }

    @Override
    public void addUpContent(AddContentDTO addContentDTO) {
        Content content = new Content();
        content.setId(addContentDTO.getId());
        content.setTitle(addContentDTO.getTitle());
        content.setEntityType(EntityType.valueOf(addContentDTO.getEntityType()));
        content.setCourseId(courseRepo.getCourseById(addContentDTO.getCourseId()));
        content.setStatus(addContentDTO.getStatus());
        if (content.getId() == null) {
            content.setCreatedDate(new Date());
        } else {
            content.setCreatedDate(this.contentRepo.getContentById(content.getId()).getCreatedDate());
        }
        switch (content.getEntityType().name()) {
            case "LECTURE":
                Lecture l = new Lecture();
                l.setContent(removeLastCharacter(addContentDTO.getContent().get("lectureContent")));
                if (!addContentDTO.getContent().get("id").isEmpty()) {
                    l.setId(Integer.parseInt(addContentDTO.getContent().get("id")));

                }
                System.out.println("Hello convert Lecture " + l);
                this.contentRepo.addUpLecture(l);
                content.setEntityId(String.valueOf(l.getId()));
                break;
            case "VIDEO":
                Video v = new Video();
                v.setUrl(removeLastCharacter(addContentDTO.getContent().get("videoUrl")));
                if (!addContentDTO.getContent().get("id").isEmpty()) {
                    v.setId(Integer.parseInt(addContentDTO.getContent().get("id")));

                }
                System.out.println("Hello convert Video " + v);
                this.contentRepo.addUpVideo(v);
                content.setEntityId(String.valueOf(v.getId()));
                break;
            case "INFORMATION":
                Information i = new Information();
                i.setContent(removeLastCharacter(addContentDTO.getContent().get("inforContent")));
                if (!addContentDTO.getContent().get("id").isEmpty()) {
                    i.setId(Integer.parseInt(addContentDTO.getContent().get("id")));

                }
                System.out.println("Hello convert Information " + i);
                this.contentRepo.addUpInformation(i);
                content.setEntityId(String.valueOf(i.getId()));
                break;

            default:
                throw new AssertionError();
        }

        content.setUpdatedDate(new Date());
        this.contentRepo.addUpContent(content);
    }

//    @Override
//    public void addUpLecture(Lecture lecture) {
//        this.contentRepo.addUpLecture(lecture);
//
//    }
//
//    @Override
//    public void addUpVideo(Video video) {
//         this.contentRepo.addUpVideo(video);
//    }
    @Override
    public AddContentDTO getContentById(int id) {
//            private Integer id;
//    private String title;
//    private CourseDTO courseId;
//    private Object content;
//    private EntityType entityType;
//    private Boolean status ;
        Content content = this.contentRepo.getContentById(id);
        AddContentDTO dto = new AddContentDTO();
        dto.setId(content.getId());
        dto.setEntityType((content.getEntityType()).name());
        dto.setTitle(content.getTitle());
        dto.setCourseId(content.getCourseId().getId());
        dto.setStatus(content.getStatus());
        Map<String, String> conMap = new HashMap<>();

        switch (content.getEntityType().name()) {
            case "LECTURE":
                Lecture l = this.contentRepo.getLectureById(Integer.parseInt(content.getEntityId()));
                System.out.println("Hello get Lecture" + l);
                conMap.put("lectureContent", l.getContent());
                conMap.put("id", String.valueOf(l.getId()));
                dto.setContent(conMap);
                break;
            case "VIDEO":
                Video v = this.contentRepo.getVideoById(Integer.parseInt(content.getEntityId()));
                System.out.println("Hello get Video" + v);
                conMap.put("videoUrl", v.getUrl());
                conMap.put("id", String.valueOf(v.getId()));
                dto.setContent(conMap);
                break;
            case "INFORMATION":
                Information i = this.contentRepo.getInformationById(Integer.parseInt(content.getEntityId()));
                System.out.println("Hello get Information" + i);
                conMap.put("inforContent", i.getContent());
                conMap.put("id", String.valueOf(i.getId()));
                dto.setContent(conMap);
                break;
            default:
                throw new AssertionError();
        }
        return dto;
    }

    @Override
    public String removeLastCharacter(String str) {
        if (str != null && str.length() > 0) {
            return str.substring(0, str.length() - 1);
        }
        return str;
    }

    @Override
    public void deleteContent(int id) {
        Content content = this.contentRepo.getContentById(id);
        System.out.println(content);
        int contendId = Integer.parseInt(content.getEntityId());
        switch (content.getEntityType().name()) {
            case "LECTURE":
                this.contentRepo.deleteLecture(contendId);
                break;
            case "VIDEO":
                this.contentRepo.deleteVideo(contendId);
                break;
            case "INFORMATION":
                this.contentRepo.deleteInformation(contendId);
                break;
            default:
                throw new AssertionError();

        }
        this.contentRepo.deleteContent(id);
    }
}
