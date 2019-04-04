package com.baizhi.service;

import com.baizhi.entity.Book;

import java.util.List;

public interface BookService {
    /**
     * @return一级页面编辑推荐查询
     */
    public List<Book> selectByRecommend();
    /**
     * @return一级页面销量查询
     */
    public List<Book> selectBySale();
    /**
     * @return一级页面新书上架查询
     */
    public List<Book> selectByCreatedate();
    /**
     * @return一级页面新书热销查询
     */
    public List<Book> selectByCreatedateAndSale();
}
