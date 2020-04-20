package com.rainier.mapper;

import com.rainier.model.DataClassify;
import com.rainier.model.DataType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DataClassifyMapper {
    DataType getDataClassifyByName(DataClassify dataClassify);

    void addDataClassify(DataClassify dataClassify);

    void updateDataClassify(DataClassify dataClassify);

    Integer getDataClassifyCount(@Param("name") String name);

    List<DataClassify> getDataClassifyList(@Param("name") String name,@Param("pageState") Integer pageState,@Param("pageSize") Integer pageSize);

    void deleteDataClassify(@Param("dataClassifyids")List dataClassifyids);

    DataClassify getDataClassifyById(@Param("dataClassifyId")Integer dataClassifyId);
}
