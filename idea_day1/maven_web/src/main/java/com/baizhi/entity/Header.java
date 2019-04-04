package com.baizhi.entity;

import java.io.Serializable;

public class Header implements Serializable{
		private String thumbnail;
		private String desc;
		private String id;
		public Header() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Header(String thumbnail, String desc, String id) {
			super();
			this.thumbnail = thumbnail;
			this.desc = desc;
			this.id = id;
		}
		@Override
		public String toString() {
			return "Header [thumbnail=" + thumbnail + ", desc=" + desc + ", id=" + id + "]";
		}
		public String getThumbnail() {
			return thumbnail;
		}
		public void setThumbnail(String thumbnail) {
			this.thumbnail = thumbnail;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
}
