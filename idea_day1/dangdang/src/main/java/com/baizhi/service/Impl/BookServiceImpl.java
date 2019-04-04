package com.baizhi.service.Impl;

import com.baizhi.dao.BookDao;
import com.baizhi.entity.Book;
import com.baizhi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class BookServiceImpl implements BookService {
   @Autowired
   private BookDao bookDao;

    @Override
    public List<Book> selectByRecommend() {
        List<Book> list = bookDao.selectByRecommend();
        List<Book> list1 = new ArrayList<Book>();
        for(int i=0;i<2;i++){
            int index = (int)(Math.random()*(list.size()-1));
            list1.add(i,list.get(index));
            list.remove(index);
        }
        return list1;
    }

    @Override
    public List<Book> selectBySale() {
        List<Book> list = bookDao.selectBySale();
        return list;
    }

    @Override
    public List<Book> selectByCreatedate() {
        List<Book> list = bookDao.selectByCreatedate();
        return list;
    }

    @Override
    public List<Book> selectByCreatedateAndSale() {
        List<Book> list =  bookDao.selectByCreatedateAndSale();
        return list;
    }
}
