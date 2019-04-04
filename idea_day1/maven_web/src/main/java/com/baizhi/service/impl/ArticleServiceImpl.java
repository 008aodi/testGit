package com.baizhi.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;
import com.baizhi.service.ArticleService;
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleDao articleDao;
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Article> findAllArticle(int page, int rows) {
		int begin = (page-1)*rows;
		int end = rows;
		return articleDao.findAllArticle(begin, end);
	}

	@Override
	public Integer findCount() {
		
		return articleDao.findCount();
	}

	@Override
	public void addArticle(Article article,String filename,String realpath,MultipartFile upfile) {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		article.setId(uuid);
		Date date = new Date();
		article.setPublicTime(date);		
		article.setArticlePic("/articleImg/"+uuid+filename);	
		filename=uuid+filename;
		//文件上传
		try {
			upfile.transferTo(new File(realpath+"\\"+filename));                                                                                                                                                                                                                          
		} catch (Exception e) {
		}
		
		articleDao.addArticle(article);
	}

	@Override
	public void deleteArticle(String id,String realpath) {
		articleDao.deleteArticle(id);
		File file = new File(realpath);
		file.delete();

	}
	
	@Override
	public Article findArticleById(String id) {
		
		return articleDao.findArticleById(id);
	}

}
