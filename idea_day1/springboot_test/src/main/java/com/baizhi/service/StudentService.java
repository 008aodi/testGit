package com.baizhi.service;

import com.baizhi.entity.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    public List<Student> findAllStudent(Student student);
    public void deleteStudent(Student student);
    public Map<String,Object> detailStudent(Student student);
    public void updateStudent(Student student);
}
