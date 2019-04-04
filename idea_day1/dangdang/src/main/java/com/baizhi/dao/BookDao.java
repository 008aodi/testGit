package com.baizhi.dao;

import com.baizhi.entity.Book;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BookDao extends Mapper<Book> {
    /**
     * @return一级页面编辑推荐查询方法
     */
    public List<Book> selectByRecommend();
    /**
     * @return一级页面热销图书查询方法
     */


    public List<Book> selectBySale();
    /**
     * @return一级页面最新上架查询方法
     */
    public List<Book> selectByCreatedate();
    /**
     * @return以及页面新书热销查询方法
     */
    public List<Book> selectByCreatedateAndSale();
}
