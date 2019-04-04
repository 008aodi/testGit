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

import com.baizhi.dao.GuruDao;
import com.baizhi.entity.Guru;
import com.baizhi.service.GuruService;
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class GuruServiceImpl implements GuruService {
	@Autowired
	private GuruDao guruDao;
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Guru> findAllGuru(int page,int rows) {
		int begin = (page-1)*rows;
		int end = rows;
		return guruDao.findAllGuru(begin, end);
	}

	@Override
	public void addGuru(Guru guru,String filename,String realpath,MultipartFile upfile) {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		guru.setId(uuid);
		Date date = new Date();
		guru.setCreateTime(date);		
		guru.setPhoto("/guruImg/"+uuid+filename);	
		filename=uuid+filename;
		//文件上传
		try {
			upfile.transferTo(new File(realpath+"\\"+filename));                                                                                                                                                                                                                          
		} catch (Exception e) {
		}
		guruDao.addGuru(guru);
		

	}

	@Override
	public void updateGuru(Guru guru) {
		guruDao.updateGuru(guru);	

	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public int findTotal() {		
		return guruDao.findTotal();
	}

	@Override
	public List<Guru> findAll() {		
		return guruDao.findAll();
	}

}
