package org.example.controller;

import org.example.model.StudentModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author Carson
 * @created 2024/11/27 星期三 下午 04:11
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @GetMapping("/test1")
    public StudentModel Test1(){
        var student = new StudentModel();
        student.setName("Carson");
        student.setAddress("Shanghai PuDong district ZhouPu county 100st");
        student.setAge(100);
        student.setSalary(new BigDecimal(100.00));
        return student;
    }

    @GetMapping("/test2")
    public StudentModel Test2(){
        var student = new StudentModel();
        student.setPrivacyKey(true);
        student.setName("Carson");
        student.setAddress("Shanghai PuDong district ZhouPu county 100st");
        student.setAge(100);
        student.setSalary(new BigDecimal(100.00));
        return student;
    }
}
