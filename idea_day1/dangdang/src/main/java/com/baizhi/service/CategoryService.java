package com.baizhi.service;

import com.baizhi.entity.Category;

import java.util.List;

public interface CategoryService {
    /**
     * @return查询所有分类接口
     */
    public List<Category> selectAllCategory();
}
