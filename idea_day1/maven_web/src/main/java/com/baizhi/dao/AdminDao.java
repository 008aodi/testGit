package com.baizhi.dao;

import org.apache.ibatis.annotations.Param;

import com.baizhi.entity.Admin;

public interface AdminDao {
		public Admin findAdmin(String name);
		public void updateAdmin(Admin admin);
}
