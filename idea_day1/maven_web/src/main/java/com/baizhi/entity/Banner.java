package com.baizhi.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @ÂÖ²¥Í¼
 *
 */
public class Banner implements Serializable{
		private String id;
		private String picName;//Ãû×Ö
		private String picPath;//Â·¾¶
		private String description;//ÃèÊö
		private int status;//×´Ì¬
		@JsonFormat(pattern="yyyy-MM-dd")
		private Date createTime;//Ê±¼ä
		private String url;//Ìø×ªÂ·¾¶
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getPicName() {
			return picName;
		}
		public void setPicName(String picName) {
			this.picName = picName;
		}
		public String getPicPath() {
			return picPath;
		}
		public void setPicPath(String picPath) {
			this.picPath = picPath;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		@Override
		public String toString() {
			return "Banner [id=" + id + ", picName=" + picName + ", picPath=" + picPath + ", description=" + description
					+ ", status=" + status + ", createTime=" + createTime + ", url=" + url + "]";
		}
		public Banner(String id, String picName, String picPath, String description, int status, Date createTime,
				String url) {
			super();
			this.id = id;
			this.picName = picName;
			this.picPath = picPath;
			this.description = description;
			this.status = status;
			this.createTime = createTime;
			this.url = url;
		}
		public Banner() {
			super();
			// TODO Auto-generated constructor stub
		}

}
