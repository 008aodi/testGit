package com.baizhi.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @计数器
 *
 */
public class Counter implements Serializable{
			private String id;
			private String name;
			private String count;//计数
			private Date lastestTime;//最后使用时间
			private  Date createTime;//创建时间
			private String userId;//所属用户
			private String courseId;//所属课程
	}
