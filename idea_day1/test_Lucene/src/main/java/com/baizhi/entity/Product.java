package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
        @Id
        private String id;
        private String name;
        private Double price;
        private String context;
        private String image;
        private String status;
        private Date createDate;
        private String produce;
}
