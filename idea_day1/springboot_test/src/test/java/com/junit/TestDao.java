package com.junit;

import com.baizhi.Application;
import com.baizhi.dao.GroupDao;
import com.baizhi.dao.StudentDao;
import com.baizhi.entity.Groups;
import com.baizhi.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestDao {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private GroupDao groupDao;
    @Test
    public void test1(){
        Student student = new Student();
        student.setId("1");
        List<Student> all = studentDao.findAllStudent(student);
        System.out.println(all);
    }
    @Test
    public void test2(){
        Groups group = new Groups();
        group.setId("1");
        Groups group1 = groupDao.selectByPrimaryKey(group);
        System.out.println(group1);

    }
}
