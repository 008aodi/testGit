package com.baizhi.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @ר��
 *
 */
public class Album implements Serializable{
	private String id;
	private String title;//����
	private Double score;//����
	private String brodecast;//����
	private String author;//����
	private String description;//���
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date publicTime;//��������
	private String count;//����
	private String coverImg;//����
	private Integer status;//״̬
	private List<Chapter> children;
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
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public String getBrodecast() {
		return brodecast;
	}
	public void setBrodecast(String brodecast) {
		this.brodecast = brodecast;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getPublicTime() {
		return publicTime;
	}
	public void setPublicTime(Date publicTime) {
		this.publicTime = publicTime;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getCoverImg() {
		return coverImg;
	}
	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public List<Chapter> getChildren() {
		return children;
	}
	public void setChildren(List<Chapter> children) {
		this.children = children;
	}
	@Override
	public String toString() {
		return "Album [id=" + id + ", title=" + title + ", score=" + score + ", brodecast=" + brodecast + ", author="
				+ author + ", description=" + description + ", publicTime=" + publicTime + ", count=" + count
				+ ", coverImg=" + coverImg + ", status=" + status + ", children=" + children + "]";
	}
	public Album() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Album(String id, String title, Double score, String brodecast, String author, String description,
			Date publicTime, String count, String coverImg, Integer status, List<Chapter> children) {
		super();
		this.id = id;
		this.title = title;
		this.score = score;
		this.brodecast = brodecast;
		this.author = author;
		this.description = description;
		this.publicTime = publicTime;
		this.count = count;
		this.coverImg = coverImg;
		this.status = status;
		this.children = children;
	}
	
	
}
