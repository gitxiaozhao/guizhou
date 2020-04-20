package com.rainier.mapper;

import com.rainier.model.DataType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DataTypeMapper {
    DataType getDataTypeByName(DataType dataType);

    void addDateType(DataType dataType);

    void updateDateType(DataType dataType);

    Integer getDataTypeCount(@Param("name") String name);

    List<DataType> getDataTypeList(@Param("name")String name, @Param("pageState")Integer pageState, @Param("pageSize")Integer pageSize);

    void deleteDataType(@Param("dataTypeids") List dataTypeids);

    DataType getDataTypeById(@Param("dataTypeId")Integer dataTypeId);
}
