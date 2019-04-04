package com.baizhi.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @上师文章
 *
 */
	public class Article implements Serializable{
		private String id;
		private String title;//标题
		private String articlePic;//标题插图
		private String content;//内容
		@JsonFormat(pattern="yyyy-MM-dd")
		private Date publicTime;//发布时间
		private String guruId;//上师Id
		private Guru guru;
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
		public String getArticlePic() {
			return articlePic;
		}
		public void setArticlePic(String articlePic) {
			this.articlePic = articlePic;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public Date getPublicTime() {
			return publicTime;
		}
		public void setPublicTime(Date publicTime) {
			this.publicTime = publicTime;
		}
		public String getGuruId() {
			return guruId;
		}
		public void setGuruId(String guruId) {
			this.guruId = guruId;
		}
		public Guru getGuru() {
			return guru;
		}
		public void setGuru(Guru guru) {
			this.guru = guru;
		}
		@Override
		public String toString() {
			return "Article [id=" + id + ", title=" + title + ", articlePic=" + articlePic + ", content=" + content
					+ ", publicTime=" + publicTime + ", guruId=" + guruId + ", guru=" + guru + "]";
		}
		public Article(String id, String title, String articlePic, String content, Date publicTime, String guruId,
				Guru guru) {
			super();
			this.id = id;
			this.title = title;
			this.articlePic = articlePic;
			this.content = content;
			this.publicTime = publicTime;
			this.guruId = guruId;
			this.guru = guru;
		}
		public Article() {
			super();
			// TODO Auto-generated constructor stub
		}

		

		
	}
