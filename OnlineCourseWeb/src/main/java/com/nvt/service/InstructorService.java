/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nvt.service;

import com.nvt.dto.AddUserDTO;
import com.nvt.dto.InstructorDTO;
import com.nvt.pojo.Instructor;
import java.util.List;
import java.util.Map;

/**
 *
 * @author TAN DAT
 */
public interface InstructorService {
    List<InstructorDTO> getAllInstructorsDTO(Map<String,String>params);
    List<Instructor> getAllInstructors(Map<String,String>params);
    void addInstructor(Instructor instructor);
    AddUserDTO getInstructorById(int id);
}
