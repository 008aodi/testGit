package com.baizhi.dao;

import org.apache.ibatis.annotations.Param;

import com.baizhi.entity.Chapter;

public interface ChapterDao {
		public void addChapter(Chapter chapter); 
		public Integer findChapter(String albumId);
		public void updateAlbumCount(@Param("id") String id, @Param("count") String count);
}
