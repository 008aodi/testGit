package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class Student implements Serializable {
    @Id
    private String id;
    private String name;
    private Integer age;
    private Date birthday;
    private String clazzId;
    private String groupId;
    private  String cityId;
    private String comment;
    @Transient
    private Clazz clazz;
    @Transient
    private City city;
    @Transient
    private List<Tag> tags;
}
