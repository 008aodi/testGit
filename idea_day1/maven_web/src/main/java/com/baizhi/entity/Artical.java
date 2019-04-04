package com.baizhi.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Ê×Ò³ÎÄÕÂ
 *
 */
public class Artical implements Serializable{
	private String  thumbnail;
	private String title;
	private String dharnaName;
	private String content;
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
	public String getDharnaName() {
		return dharnaName;
	}
	public void setDharnaName(String dharnaName) {
		this.dharnaName = dharnaName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	@Override
	public String toString() {
		return "Artical [thumbnail=" + thumbnail + ", title=" + title + ", dharnaName=" + dharnaName + ", content="
				+ content + ", create_date=" + create_date + "]";
	}
	public Artical(String thumbnail, String title, String dharnaName, String content, Date create_date) {
		super();
		this.thumbnail = thumbnail;
		this.title = title;
		this.dharnaName = dharnaName;
		this.content = content;
		this.create_date = create_date;
	}
	public Artical() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
