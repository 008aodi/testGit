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

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class BannerServiceImpl implements BannerService {
	@Autowired
	private BannerDao bannerDao;
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Banner> findAllBanner(int page,int rows) {
		int begin = (page-1)*rows;
		int end = rows;
		return bannerDao.findAllBanner(begin,end);
	}
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public Integer findTotal() {		
		return bannerDao.findCount();
	}
	@Override
	public void addBanner(Banner banner,String filename,String realpath,MultipartFile upfile) {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		banner.setId(uuid);
		Date date = new Date();
		banner.setCreateTime(date);		
		banner.setPicPath("/bannerImage/"+uuid+filename);	
		filename=uuid+filename;
		//文件上传
		try {
			upfile.transferTo(new File(realpath+"\\"+filename));                                                                                                                                                                                                                          
		} catch (Exception e) {
		}
		
		
		
		bannerDao.addBanner(banner);
	}
	public void deleteBanner(String id,String realpath){
		bannerDao.deleteBanner(id);
		File file = new File(realpath);
		file.delete();
	}
	@Override
	public void updateBanner(Banner banner) {
		bannerDao.updateBanner(banner);	
	}
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public Banner findBannerById(String id) {
		
		return bannerDao.findBannerById(id);
	}
}
