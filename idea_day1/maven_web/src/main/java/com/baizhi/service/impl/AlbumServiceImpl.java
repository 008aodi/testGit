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

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class AlbumServiceImpl implements AlbumService {
	@Autowired
	private AlbumDao albumDao;
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Album> findAllAlbum(int page,int rows) {
		int begin = (page-1)*rows;
		return albumDao.findAllAlbum(begin,rows);
	}
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public Integer findTotal() {
		return albumDao.findTotal();
	}
	@Override
	public void addAlbum(Album album,String filename,String realpath,MultipartFile upfile) {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		album.setId(uuid);
		Date date = new Date();
		album.setPublicTime(date);		
		album.setCoverImg(uuid+filename);	
		filename=uuid+filename;
		//文件上传
		try {
			upfile.transferTo(new File(realpath+"\\"+filename));                                                                                                                                                                                                                          
		} catch (Exception e) {
		}
		
		albumDao.addAlbum(album);		
	}

}
