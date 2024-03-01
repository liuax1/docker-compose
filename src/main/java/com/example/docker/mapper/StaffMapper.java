package com.example.docker.mapper;

import com.example.docker.entities.Staff;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;

@Mapper
public interface StaffMapper extends BaseMapper<Staff> {
}