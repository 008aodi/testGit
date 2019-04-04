package com.baizhi.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @功课
 *
 */
public class Course implements Serializable{
		private String id;
		private String name;//名称
		private String userId;//所属用户
		private Integer type;//类型
		private Date createTime;//创建时间
}
