package com.baizhi.dao;


import com.baizhi.entity.Student;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface StudentDao extends Mapper<Student> {
    public  List<Student> findAllStudent(Student student);
}
