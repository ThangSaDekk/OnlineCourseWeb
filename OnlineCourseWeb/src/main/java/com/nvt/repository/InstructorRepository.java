/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nvt.repository;

import com.nvt.pojo.Instructor;
import java.util.List;
import java.util.Map;

/**
 *
 * @author thang
 */
public interface InstructorRepository {

    List<Instructor> getAllInstructors(Map<String, String> params);

    Instructor addInstructor(Instructor instructor);

    Instructor getInstructorById(int id);

    Instructor getInstructorByUserId(int userId);

}
