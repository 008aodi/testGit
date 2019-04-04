package com.baizhi.entity;


import java.io.Serializable;
import java.util.Date;

import lombok.Data;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@TableName(value = "user")
public class User implements Serializable {

    @TableId(value = "id", type = IdType.AUTO) // 主键自增长
    private Integer id;
    @TableField(value = "name")
    private String name;
    @TableField(value = "age")
    private Integer age;
    @JsonFormat(pattern="yyyyMMddHHmmssSSS", timezone="GMT+8")
    @TableField(value = "birth")
    private Date birth;

}
