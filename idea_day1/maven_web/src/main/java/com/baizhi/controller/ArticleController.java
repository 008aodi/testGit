package com.baizhi.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;

@Controller
@RequestMapping("/article")
public class ArticleController {
		@Autowired
		private ArticleService articleService;
		@RequestMapping("/findall.do")
		@ResponseBody
		public Map<String,Object> findAllBanner(int page,int rows){
			List<Article> articles = articleService.findAllArticle(page,rows);
			Map<String,Object> map = new HashMap<String,Object>();
			Integer total = articleService.findCount();
			map.put("total", total);
			map.put("rows", articles);
			return map;
		}
		@RequestMapping("/add.do")
		@ResponseBody
		public String  addArticle(Article article,MultipartFile upfile,HttpServletRequest request){
			//获取图片路径
			String realpath = request.getSession().getServletContext().getRealPath("/articleImg");
			//获取原始文件名
			String filename = upfile.getOriginalFilename();		
			File f=new File(realpath+"/");//创建文件夹
	        if (!f.exists()){
	           f.mkdir();
	        }
	        articleService.addArticle(article,filename,realpath,upfile);
			return "ok";
		}
		@RequestMapping("/delete.do")
		@ResponseBody
		public String deleteBanner(String id,HttpServletRequest request){		
			 Article article = articleService.findArticleById(id);
			String articlePic = article.getArticlePic();
			String realpath = request.getSession().getServletContext().getRealPath(articlePic);
			articleService.deleteArticle(id,realpath);		
			return "ok";
		}
}
