package com.baizhi.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.baizhi.entity.Album;

public interface AlbumService {
		public List<Album> findAllAlbum(int page, int rows);
		public Integer findTotal();
		public void addAlbum(Album album, String filename, String realpath, MultipartFile upfile);
}
