package com.baizhi.controller;

import com.baizhi.entity.Student;
import com.baizhi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
   @RequestMapping("/findall")
   @ResponseBody
   public  List<Student> findAllStudent(Student student){
       List<Student> students = studentService.findAllStudent(student);
       return students;
   }
   @RequestMapping("/delete")
   public String deleteStudent(Student student){
       studentService.deleteStudent(student);
       return "redirect:/back/student/findall.jsp";
   }
   @RequestMapping("/detail")
   public String detailStudent(Student student,Model model){
       Map<String, Object> map = studentService.detailStudent(student);
       model.addAttribute("student",map.get("student"));
       model.addAttribute("groups",map.get("groups"));
       return "back/student/detail";
   }
}
