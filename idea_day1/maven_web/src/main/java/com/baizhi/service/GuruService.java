package com.baizhi.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.baizhi.entity.Guru;

public interface GuruService {
	public List<Guru> findAllGuru(int page, int rows);
	public void addGuru(Guru guru, String filename, String realpath, MultipartFile upfile);
	public void updateGuru(Guru guru);
	public int findTotal();
	public List<Guru> findAll();
}
