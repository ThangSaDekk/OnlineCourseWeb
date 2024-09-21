/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvt.service.impl;

import com.nvt.pojo.Process;
import com.nvt.repository.ProcessRepository;
import com.nvt.service.ProcessService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author thang
 */
@Service
public class ProcessServiceImpl implements ProcessService{
    @Autowired
    private ProcessRepository processRepo;

    @Override
    public List<Process> getProcessList(Map<String, String> params) {
        return this.processRepo.getProcessList(params);
    }
    
}
