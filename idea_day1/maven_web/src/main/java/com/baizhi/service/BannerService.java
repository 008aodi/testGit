package com.baizhi.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.baizhi.entity.Banner;

public interface BannerService {
		public List<Banner> findAllBanner(int page, int rows);
		public Integer findTotal();
		public void addBanner(Banner banner, String filename, String realpath, MultipartFile upfile);
		public void deleteBanner(String id, String realpath);
		public void updateBanner(Banner banner);
		public Banner findBannerById(String id);
}
