package com.baizhi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baizhi.entity.Banner;

public interface BannerDao {
	public List<Banner> findAllBanner(@Param("begin") Integer begin, @Param("end") Integer end);
	public Integer findCount();
	public void addBanner(Banner banner);
	public void deleteBanner(String id);
	public void updateBanner(Banner banner);
	public Banner findBannerById(String id);
}
