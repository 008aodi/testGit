package com.baizhi.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;

public interface ArticleService {
	public List<Article> findAllArticle(int page, int rows);
	public Integer findCount();
	public void addArticle(Article article, String filename, String realpath, MultipartFile upfile);
	public void deleteArticle(String id, String realpath);
	public Article findArticleById(String id);
}
