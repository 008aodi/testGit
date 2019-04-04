package com.baizhi.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;

@Controller
@RequestMapping("/chapter")
public class ChapterController {
		@Autowired
		private ChapterService chapterService;	
		
		
	@RequestMapping("/addchapter.do")
	@ResponseBody
	public String addChapter(Chapter chapter,MultipartFile upfile,HttpServletRequest request){
		String realPath = request.getSession().getServletContext().getRealPath("/chapter");		
		File f=new File(realPath+"/");//创建文件夹
        if (!f.exists()){
           f.mkdir();
        }
        String filename = upfile.getOriginalFilename();	
        chapterService.addChapter(chapter, filename, realPath, upfile);
		return "ok";
	}
}
