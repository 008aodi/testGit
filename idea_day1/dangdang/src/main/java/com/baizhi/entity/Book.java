package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="bb_book")
public class Book implements Serializable {
    @Id
    private String id;
    private String name;
    private String author;//作者
    private String cover;//存放图书图片路径
    private String press;//出版社
    private Date press_date;//出版日期
    private String edition;//版本
    private Date printDate;//印刷日期
    private String impression;//
    private String ISBN;//国际标准图书编号
    private Integer word_num;
    private Integer page_num;
    private String sizes;
    private String paper;
    private String pack;
    private Double price;//价格
    private Double discount;//折扣
    private Date create_date;//上架日期
    private String editor_recommend;//编辑推荐
    private String content_abstract;//内容摘要
    private String author_abstract;//作者摘要
    private String directory;//目录
    private String media_commentary;//媒体评价
    private Integer sale;//销量
    private String category_id;//分类id
}
