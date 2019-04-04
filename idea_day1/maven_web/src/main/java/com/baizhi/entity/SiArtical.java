package com.baizhi.entity;

import java.util.Date;

public class SiArtical {
	private String  thumbnail;
	private String title;
	private String dharnaName;
	private String content;
	private Date create_date;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "SiArtical [thumbnail=" + thumbnail + ", title=" + title + ", dharnaName=" + dharnaName + ", content="
				+ content + ", create_date=" + create_date + ", type=" + type + "]";
	}
	public SiArtical(String thumbnail, String title, String dharnaName, String content, Date create_date, String type) {
		super();
		this.thumbnail = thumbnail;
		this.title = title;
		this.dharnaName = dharnaName;
		this.content = content;
		this.create_date = create_date;
		this.type = type;
	}
	public SiArtical() {
		super();
		this.type="1";
	}
	
}
