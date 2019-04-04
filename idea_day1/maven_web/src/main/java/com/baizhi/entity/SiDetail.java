package com.baizhi.entity;

import java.io.Serializable;

public class SiDetail implements Serializable{
		private String link;
		private String id;
		private String ext;
		public String getLink() {
			return link;
		}
		public void setLink(String link) {
			this.link = link;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getExt() {
			return ext;
		}
		public void setExt(String ext) {
			this.ext = ext;
		}
		@Override
		public String toString() {
			return "SiDetail [link=" + link + ", id=" + id + ", ext=" + ext + "]";
		}
		public SiDetail(String link, String id, String ext) {
			super();
			this.link = link;
			this.id = id;
			this.ext = ext;
		}
		public SiDetail() {
			super();
			// TODO Auto-generated constructor stub
		}
		
}
