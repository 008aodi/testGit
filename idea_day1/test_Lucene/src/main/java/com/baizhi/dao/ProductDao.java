package com.baizhi.dao;

import com.baizhi.entity.Product;

import java.util.List;

public interface ProductDao {
    public List<Product> query(String type, String text);
    public void insert(Product product);
    public void delete(String id);
    public void update(Product product);
}
