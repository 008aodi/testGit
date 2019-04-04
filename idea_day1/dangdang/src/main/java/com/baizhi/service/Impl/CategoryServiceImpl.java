package com.baizhi.service.Impl;

import com.baizhi.dao.CategoryDao;
import com.baizhi.entity.Category;
import com.baizhi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
        @Autowired
        private CategoryDao categoryDao;
    @Override
    public List<Category> selectAllCategory() {
        List<Category> list = categoryDao.selectAllCategory();
        return list;
    }
}
