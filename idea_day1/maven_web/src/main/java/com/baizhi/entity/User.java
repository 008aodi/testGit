package com.baizhi.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
	private String id;
	private String photo;//头像
	private String dharmaName;//法名
	private String name;//名称
	private Integer sex;
	private String  province;//省份
	private String city;//城市
	private String sign;//签名
	private String phoneNum;
	private String password;
	private String salt;//盐
	private Date creatTime;//创建时间
	private Integer status; //用户状态
	private String guruId;
	private String location;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getDharmaName() {
		return dharmaName;
	}
	public void setDharmaName(String dharmaName) {
		this.dharmaName = dharmaName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
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
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getGuruId() {
		return guruId;
	}
	public void setGuruId(String guruId) {
		this.guruId = guruId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", photo=" + photo + ", dharmaName=" + dharmaName + ", name=" + name + ", sex=" + sex
				+ ", province=" + province + ", city=" + city + ", sign=" + sign + ", phoneNum=" + phoneNum
				+ ", password=" + password + ", salt=" + salt + ", creatTime=" + creatTime + ", status=" + status
				+ ", guruId=" + guruId + ", location=" + location + "]";
	}
	public User(String id, String photo, String dharmaName, String name, Integer sex, String province, String city,
			String sign, String phoneNum, String password, String salt, Date creatTime, Integer status, String guruId,
			String location) {
		super();
		this.id = id;
		this.photo = photo;
		this.dharmaName = dharmaName;
		this.name = name;
		this.sex = sex;
		this.province = province;
		this.city = city;
		this.sign = sign;
		this.phoneNum = phoneNum;
		this.password = password;
		this.salt = salt;
		this.creatTime = creatTime;
		this.status = status;
		this.guruId = guruId;
		this.location = location;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
