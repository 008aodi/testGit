package com.baizhi.entity;

import java.io.Serializable;

public class WenChapter implements Serializable{
		private String title;
		private String download_url;
		private String size;
		private String duration;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDownload_url() {
			return download_url;
		}
		public void setDownload_url(String download_url) {
			this.download_url = download_url;
		}
		public String getSize() {
			return size;
		}
		public void setSize(String size) {
			this.size = size;
		}
		public String getDuration() {
			return duration;
		}
		public void setDuration(String duration) {
			this.duration = duration;
		}
		@Override
		public String toString() {
			return "WenChpater [title=" + title + ", download_url=" + download_url + ", size=" + size + ", duration="
					+ duration + "]";
		}
		public WenChapter(String title, String download_url, String size, String duration) {
			super();
			this.title = title;
			this.download_url = download_url;
			this.size = size;
			this.duration = duration;
		}
		public WenChapter() {
			super();
			// TODO Auto-generated constructor stub
		}
		

}
