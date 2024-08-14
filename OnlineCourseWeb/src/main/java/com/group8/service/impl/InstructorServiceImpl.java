/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group8.service.impl;

import com.group8.dto.InstructorDTO;
import com.group8.pojo.Instructor;
import com.group8.repository.InstructorRepository;
import com.group8.service.InstructorService;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author thang
 */
@Service
public class InstructorServiceImpl implements InstructorService{

    @Autowired
    private InstructorRepository instructorRepo;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public List<InstructorDTO> getAllInstructorsDTO() {
         List<Instructor> instructors = this.instructorRepo.getAllInstructors();
        return instructors.stream()
                .map(i -> {
                    InstructorDTO instructorDTO = new InstructorDTO();
                    modelMapper.map(i, instructorDTO);
                    return instructorDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return this.instructorRepo.getAllInstructors();
    }
    
}
