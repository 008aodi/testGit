package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="bb_category")
public class Category implements Serializable {
        @Id
        private String id;
        private String name;
        private String parent_id;//父分类id
        private Integer levels;  //分类等级
        @Transient
        private List<Category> list;
        @Transient
        private Integer account;
}
