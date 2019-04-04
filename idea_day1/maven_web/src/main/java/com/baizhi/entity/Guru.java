package com.baizhi.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @上师
 *
 */
public class Guru implements Serializable{
		private String id;
		private String dharnaName;//法名
		private String photo;//头像
		private Integer status;//状态
		@JsonFormat(pattern="yyyy-MM-dd")
		private Date createTime;//创建时间
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getDharnaName() {
			return dharnaName;
		}
		public void setDharnaName(String dharnaName) {
			this.dharnaName = dharnaName;
		}
		public String getPhoto() {
			return photo;
		}
		public void setPhoto(String photo) {
			this.photo = photo;
		}
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		@Override
		public String toString() {
			return "Guru [id=" + id + ", dharnaName=" + dharnaName + ", photo=" + photo + ", status=" + status
					+ ", createTime=" + createTime + "]";
		}
		public Guru(String id, String dharnaName, String photo, Integer status, Date createTime) {
			super();
			this.id = id;
			this.dharnaName = dharnaName;
			this.photo = photo;
			this.status = status;
			this.createTime = createTime;
		}
		public Guru() {
			super();
			// TODO Auto-generated constructor stub
		}

		
}
