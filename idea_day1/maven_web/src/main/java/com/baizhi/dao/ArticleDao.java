package com.baizhi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;

public interface ArticleDao {
	public List<Article> findAllArticle(@Param("begin") Integer begin, @Param("end") Integer end);
	public Integer findCount();
	public void addArticle(Article article);
	public void deleteArticle(String id);
	public Article findArticleById(String id);
}
