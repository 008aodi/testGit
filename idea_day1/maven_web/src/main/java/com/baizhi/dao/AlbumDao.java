package com.baizhi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baizhi.entity.Album;

public interface AlbumDao {
		public List<Album> findAllAlbum(@Param("begin") int begin, @Param("end") int end);
		public Integer findTotal();
		public void addAlbum(Album album);
}
