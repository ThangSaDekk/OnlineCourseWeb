/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvt.controller;

import com.nvt.pojo.User;
import com.nvt.service.UserService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author thang
 */
@Controller
@ControllerAdvice
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private Environment env;

    @RequestMapping("/students")
    public String courseView(Model model, @RequestParam Map<String, String> params) {
        if (params.get("page") == null) {
            params.put("page", "1");
        }
        List<User> students = this.userService.getUserList(params);

        Map<String, String> studentparams = new HashMap<>();
        int total = this.userService.getUserList(studentparams).size();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (!entry.getKey().equals("page") && entry.getValue() != null && !entry.getValue().isEmpty()) {
                params.remove("page");
                total = this.userService.getUserList(params).size();
                break;
            }
        }
        int PAGE_MAX = Integer.parseInt(env.getProperty("page.size.student"));

        int pageTotal = (int) Math.ceil((double) total / PAGE_MAX);

        model.addAttribute("students", students);
        model.addAttribute("pageTotal", pageTotal); // gửi biến tổng trang để phân trang
        return "student";
    }

    @GetMapping("/students/{id}/lock")
    public String lockUser(@PathVariable(value = "id") int id) {
        this.userService.addUpUser(this.userService.getUserByID(id));
        return "redirect:/students";
    }

    @GetMapping("/students/{id}/unlock")
    public String unLockUser(@PathVariable(value = "id") int id) {
        this.userService.addUpUser(this.userService.getUserByID(id));
        return "redirect:/students";
    }
}
