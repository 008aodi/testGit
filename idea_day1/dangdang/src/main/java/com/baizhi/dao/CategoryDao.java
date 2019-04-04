package com.baizhi.dao;

import com.baizhi.entity.Category;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CategoryDao extends Mapper<Category> {
    public List<Category> selectAllCategory();
}
