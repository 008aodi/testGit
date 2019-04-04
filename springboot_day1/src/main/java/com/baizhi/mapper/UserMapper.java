package com.baizhi.mapper;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;


@Mapper
public interface UserMapper extends BaseMapper<User> {

}