package com.baizhi.controller;

import com.baizhi.entity.Category;
import com.baizhi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/c")
@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @RequestMapping("/selectAllCategory")
    public String selectAllCategory(Model model) {
        List<Category> categorys = categoryService.selectAllCategory();
        model.addAttribute("categorys", categorys);
        return "dangdang/main/category";
    }

}
