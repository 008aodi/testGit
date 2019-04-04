package com.baizhi.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class WenBody implements Serializable{
	private String thumbnail;
	private String title;
	private String author;
	@JsonFormat(pattern="yyyy.MM.dd")
	private Date create_date;
	private String set_count;
	private String type;
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getSet_count() {
		return set_count;
	}
	public void setSet_count(String set_count) {
		this.set_count = set_count;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "WenBody [thumbnail=" + thumbnail + ", title=" + title + ", author=" + author + ", create_date="
				+ create_date + ", set_count=" + set_count + ", type=" + type + "]";
	}
	public WenBody(String thumbnail, String title, String author, Date create_date, String set_count, String type) {
		super();
		this.thumbnail = thumbnail;
		this.title = title;
		this.author = author;
		this.create_date = create_date;
		this.set_count = set_count;
		this.type = type;
	}
	public WenBody() {
		super();
		this.type="0";
	}
	
}
