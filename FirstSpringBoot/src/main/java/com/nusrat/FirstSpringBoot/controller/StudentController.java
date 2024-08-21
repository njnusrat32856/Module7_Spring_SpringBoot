package com.nusrat.FirstSpringBoot.controller;

import com.nusrat.FirstSpringBoot.entity.Student;
import com.nusrat.FirstSpringBoot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/savestudentform")
    public String saveStudent(Model m){
        m.addAttribute("student", new Student());
        m.addAttribute("title", "Add New Student");
        return "savestudentform";
    }

    @PostMapping("/savestudent")
    public String saveStudent(@ModelAttribute("student") Student student){

        studentService.saveStu(student);
        return "redirect:/";
    }

}
