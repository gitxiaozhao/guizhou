package com.rainier.mapper;

import com.rainier.model.Log;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface LogMapper {
    List<Log> selectByPage(@Param("username") String username, @Param("start") Timestamp start, @Param("end") Timestamp end, @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);
    Integer selectNumBer(@Param("username") String username, @Param("start") Timestamp start, @Param("end") Timestamp end);
    void insertSelective(Log log);
}