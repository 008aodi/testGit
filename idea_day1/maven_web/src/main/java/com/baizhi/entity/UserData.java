package com.baizhi.entity;

import java.io.Serializable;

public class UserData implements Serializable{
		private String password;
		private String farmington;
		private String uid;
		private String nickname;
		private String gender;
		private String photo;
		private String location;
		private String province;
		private String city;
		private String description;
		private String phone;
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getFarmington() {
			return farmington;
		}
		public void setFarmington(String farmington) {
			this.farmington = farmington;
		}
		public String getUid() {
			return uid;
		}
		public void setUid(String uid) {
			this.uid = uid;
		}
		public String getNickname() {
			return nickname;
		}
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getPhoto() {
			return photo;
		}
		public void setPhoto(String photo) {
			this.photo = photo;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public String getProvince() {
			return province;
		}
		public void setProvince(String province) {
			this.province = province;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		@Override
		public String toString() {
			return "UserData [password=" + password + ", farmington=" + farmington + ", uid=" + uid + ", nickname="
					+ nickname + ", gender=" + gender + ", photo=" + photo + ", location=" + location + ", province="
					+ province + ", city=" + city + ", description=" + description + ", phone=" + phone + "]";
		}
		public UserData(String password, String farmington, String uid, String nickname, String gender, String photo,
				String location, String province, String city, String description, String phone) {
			super();
			this.password = password;
			this.farmington = farmington;
			this.uid = uid;
			this.nickname = nickname;
			this.gender = gender;
			this.photo = photo;
			this.location = location;
			this.province = province;
			this.city = city;
			this.description = description;
			this.phone = phone;
		}
		public UserData() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
}
