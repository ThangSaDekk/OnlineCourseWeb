/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvt.service;

import java.util.List;
import java.util.Map;
import com.nvt.pojo.Process;

/**
 *
 * @author thang
 */
public interface ProcessService {

    List<Process> getProcessList(Map<String, String> params);
}
