package com.baizhi.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @专辑章节
 *
 */
public class Chapter implements Serializable{
	private String id;
	private String title;//名称
	private String url;//音频路径
	private Double size;//音频大小
	private String length;//音频时长
	private String times;//集数
	private Date createTime;//上传时间
	private String AlbumId;//所属专辑
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Double getSize() {
		return size;
	}
	public void setSize(Double size) {
		this.size = size;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getAlbumId() {
		return AlbumId;
	}
	public void setAlbumId(String albumId) {
		AlbumId = albumId;
	}
	@Override
	public String toString() {
		return "Chapter [id=" + id + ", title=" + title + ", url=" + url + ", size=" + size + ", length=" + length
				+ ", times=" + times + ", createTime=" + createTime + ", AlbumId=" + AlbumId + "]";
	}
	public Chapter() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Chapter(String id, String title, String url, Double size, String length, String times, Date createTime,
			String albumId) {
		super();
		this.id = id;
		this.title = title;
		this.url = url;
		this.size = size;
		this.length = length;
		this.times = times;
		this.createTime = createTime;
		AlbumId = albumId;
	}

	
}
