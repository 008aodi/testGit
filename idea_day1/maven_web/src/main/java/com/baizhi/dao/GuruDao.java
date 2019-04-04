package com.baizhi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.baizhi.entity.Guru;

public interface GuruDao {
		public List<Guru> findAllGuru(@Param("begin") Integer begin, @Param("end") Integer end);
		public void addGuru(Guru guru);
		public void updateGuru(Guru guru);
		public int findTotal();
		public List<Guru> findAll();
}
