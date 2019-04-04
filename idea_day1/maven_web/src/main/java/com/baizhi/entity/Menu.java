package com.baizhi.entity;

import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable{
		private String id;
		private String title;//标题
		private String href;//路径
		private String iconCls;//图标
		private String parentId;//父菜单Id
		private List<Menu> list;
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
		public String getHref() {
			return href;
		}
		public void setHref(String href) {
			this.href = href;
		}
		public String getIconCls() {
			return iconCls;
		}
		public void setIconCls(String iconCls) {
			this.iconCls = iconCls;
		}
		public String getParentId() {
			return parentId;
		}
		public void setParentId(String parentId) {
			this.parentId = parentId;
		}
		public List<Menu> getList() {
			return list;
		}
		public void setList(List<Menu> list) {
			this.list = list;
		}
		public Menu(String id, String title, String href, String iconCls, String parentId, List<Menu> list) {
			super();
			this.id = id;
			this.title = title;
			this.href = href;
			this.iconCls = iconCls;
			this.parentId = parentId;
			this.list = list;
		}
		public Menu() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "Menu [id=" + id + ", title=" + title + ", href=" + href + ", iconCls=" + iconCls + ", parentId="
					+ parentId + ", list=" + list + "]";
		}

}
