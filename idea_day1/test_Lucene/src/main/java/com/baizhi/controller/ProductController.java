package com.baizhi.controller;

import com.baizhi.dao.ProductDao;
import com.baizhi.entity.Product;
import com.baizhi.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/product")
public class ProductController{
    @Autowired
    private ProductDao productDao;
    @RequestMapping("/query")
    @ResponseBody
    public List<Product> queryProduct(String type,String text){
        List<Product> query = productDao.query(type, text);
        return query;
    }
    @RequestMapping("/insert")
    @ResponseBody
    public String insertProduct(Product product, MultipartFile upfile, HttpSession session,String dateString){
        Date date = DateUtil.getDate(dateString);
        product.setCreateDate(date);
        String id = UUID.randomUUID().toString().replace("-", "");
        product.setId(id);
        String realPath = session.getServletContext().getRealPath("/image");
        String filename = upfile.getOriginalFilename();
        product.setImage("/image/"+filename);
        productDao.insert(product);
        try {
            upfile.transferTo(new File(realPath+"\\"+filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }
}
