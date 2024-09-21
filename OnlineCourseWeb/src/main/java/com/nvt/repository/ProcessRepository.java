/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvt.repository;

import com.nvt.pojo.Process;
import java.util.List;
import java.util.Map;

/**
 *
 * @author thang
 */
public interface ProcessRepository {
    double checkProcess(int userId, int contentId);
    
    void addUpProcess(Process process);
    
    List<Process> getProcessList(Map<String,String> params);
}
