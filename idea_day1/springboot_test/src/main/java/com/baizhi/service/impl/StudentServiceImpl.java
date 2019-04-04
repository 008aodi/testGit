package com.baizhi.service.impl;

import com.baizhi.dao.GroupDao;
import com.baizhi.dao.StudentDao;
import com.baizhi.entity.Groups;
import com.baizhi.entity.Student;
import com.baizhi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private GroupDao groupDao;
    @Override
    public List<Student> findAllStudent(Student student){
        List<Student> students = studentDao.findAllStudent(student);
        return students;
    }

    @Override
    public void deleteStudent(Student student) {
        studentDao.delete(student);
    }

    @Override
    public Map<String, Object> detailStudent(Student student) {

        List<Student> all = studentDao.findAllStudent(student);
        Student student1 = all.get(0);
        System.out.println(student1);
        String groupId = student1.getGroupId();
        Groups groups = new Groups();
        groups.setId(groupId);
        Groups group1 = groupDao.selectByPrimaryKey(groups);
        HashMap<String, Object> map = new HashMap<>();
        map.put("student",student1);
        map.put("groups",group1);
        return map;
    }

    @Override
    public void updateStudent(Student student) {
         studentDao.updateByPrimaryKey(student);
    }
}
