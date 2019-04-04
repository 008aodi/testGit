package com.baizhi.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class WenDetail implements Serializable{
	  private String thumbnail;
	  private String title;
	  private double score;
	  private String author;
	  private String broadcast;
	  private String set_count;
	  private String brief;
	  @JsonFormat(pattern="yyyy.MM.dd")
	  private Date create_date;
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
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getBroadcast() {
		return broadcast;
	}
	public void setBroadcast(String broadcast) {
		this.broadcast = broadcast;
	}
	public String getSet_count() {
		return set_count;
	}
	public void setSet_count(String set_count) {
		this.set_count = set_count;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	@Override
	public String toString() {
		return "WenDetail [thumbnail=" + thumbnail + ", title=" + title + ", score=" + score + ", author=" + author
				+ ", broadcast=" + broadcast + ", set_count=" + set_count + ", brief=" + brief + ", create_date="
				+ create_date + "]";
	}
	public WenDetail(String thumbnail, String title, double score, String author, String broadcast, String set_count,
			String brief, Date create_date) {
		super();
		this.thumbnail = thumbnail;
		this.title = title;
		this.score = score;
		this.author = author;
		this.broadcast = broadcast;
		this.set_count = set_count;
		this.brief = brief;
		this.create_date = create_date;
	}
	public WenDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	  
}
