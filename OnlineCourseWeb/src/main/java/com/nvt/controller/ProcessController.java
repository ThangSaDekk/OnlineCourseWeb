/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvt.controller;

import com.nvt.pojo.Process;
import com.nvt.service.ProcessService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author thang
 */
@Controller
@ControllerAdvice
public class ProcessController {

    @Autowired
    private ProcessService processSer;
    
    @Autowired
    private Environment env;


    @RequestMapping("/processes")
    public String creatView(Model model, @RequestParam Map<String, String> params) {
        if (params.get("page") == null) {
            params.put("page", "1");
        }
        List<Process> processes = this.processSer.getProcessList(params);

        int total = this.processSer.getProcessList(null).size();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (!entry.getKey().equals("page") && entry.getValue() != null && !entry.getValue().isEmpty()) {
                params.remove("page");
                total = this.processSer.getProcessList(params).size();
                break;
            }
        }
        int PAGE_MAX = Integer.parseInt(env.getProperty("page.size.process"));

        int pageTotal = (int) Math.ceil((double) total / PAGE_MAX);
       
        model.addAttribute("processes", processes);
        model.addAttribute("pageTotal", pageTotal); // gửi biến tổng trang để phân trang
        return "process";
    }
}
