package com.baizhi.controller;

import com.baizhi.entity.Book;
import com.baizhi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/b")
public class BookController {
    @Autowired
    private BookService bookService;
    /**
     * @return一级页面查询编辑推荐
     */
    @RequestMapping("/recommend")
    public String selectByRecommend(Model model){
        List<Book> recommends = bookService.selectByRecommend();
        model.addAttribute("recommends",recommends);
        return "dangdang/main/recommend";
    }
    /**
     * @return一级页面热销图书查询
     */
    @RequestMapping("/sale")
    public String selectBySale(Model model){
        List<Book> list = bookService.selectBySale();
        model.addAttribute("list",list);
        return "dangdang/main/hot";
    }
    /**
     * @return一级页面最新上架查询
     */
    @RequestMapping("/createdate")
    public String selectByCreatedate(Model model){
        List<Book> news = bookService.selectByCreatedate();
        model.addAttribute("news",news);
        return "dangdang/main/new";
    }
    /**
     * @return一级页面新书热销查询
     */
    @RequestMapping("/createdateandsale")
    public String selectByCreatedateAndSale(Model model){
        List<Book> newhots = bookService.selectByCreatedateAndSale();
        model.addAttribute("newhot",newhots);
        return "dangdang/main/hotBoard";
    }

}
