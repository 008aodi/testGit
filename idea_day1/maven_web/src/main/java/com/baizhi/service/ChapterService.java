package com.baizhi.service;

import org.springframework.web.multipart.MultipartFile;

import com.baizhi.entity.Chapter;

public interface ChapterService {
		public void addChapter(Chapter chapter, String filename, String realpath, MultipartFile upfile);
}
