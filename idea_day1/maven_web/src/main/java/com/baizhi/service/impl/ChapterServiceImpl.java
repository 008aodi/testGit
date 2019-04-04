package com.baizhi.service.impl;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class ChapterServiceImpl implements ChapterService {
	@Autowired
	private ChapterDao chapterDao;
	@Override
	public void addChapter(Chapter chapter,String filename,String realpath,MultipartFile upfile) {		
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		chapter.setId(uuid);
		Date date = new Date();
		chapter.setCreateTime(date);
		chapter.setTitle(filename);
		Integer times = chapterDao.findChapter(chapter.getAlbumId());
		times =times+1;
		chapter.setTimes(times.toString());
		long size = upfile.getSize();
		double mb= (double)(size/1024.0/1024.0);		
		   chapter.setSize(mb);         
							
		chapter.setUrl("/chapter/"+filename);	
		Encoder coder = new Encoder();
		//文件上传
		try {
			upfile.transferTo(new File(realpath+"\\"+filename));  		
			MultimediaInfo info = coder.getInfo(new File(realpath+"\\"+filename));
			long los = info.getDuration();
			String ss = los/60000+"分"+(los/1000-los/60000*60)+"秒";
			chapter.setLength(ss);
		} catch (Exception e) {
		}		
		chapterDao.addChapter(chapter);
		chapterDao.updateAlbumCount(chapter.getAlbumId(),times.toString());
	}

}
