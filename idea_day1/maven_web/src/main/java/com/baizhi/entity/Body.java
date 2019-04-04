package com.baizhi.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Ê×Ò³×¨¼­
 *
 */
public class Body implements Serializable{
		private String thumbnail;
		private String title;
		private String author;
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
		@Override
		public String toString() {
			return "Body [thumbnail=" + thumbnail + ", title=" + title + ", author=" + author + ", create_date="
					+ create_date + "]";
		}
		public Body(String thumbnail, String title, String author, Date create_date) {
			super();
			this.thumbnail = thumbnail;
			this.title = title;
			this.author = author;
			this.create_date = create_date;
		}
		public Body() {
			super();
			// TODO Auto-generated constructor stub
		}

		
		
		
}
