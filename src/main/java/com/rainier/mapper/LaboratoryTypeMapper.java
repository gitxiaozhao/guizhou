package com.rainier.mapper;

import com.rainier.model.LaboratoryType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LaboratoryTypeMapper {
    void updateLaboratoryType(@Param("laboratoryType") LaboratoryType laboratoryType);

    void addLaboratoryType(@Param("laboratoryType") LaboratoryType laboratoryType);

    LaboratoryType getLaboratoryTypeById(@Param("id") Integer id);

    Integer queryLaboratoryTypeCount(@Param("key") String key);

    List<LaboratoryType> queryLaboratoryTypeList(@Param("key") String key,@Param("pageIndex") Integer pageIndex,@Param("pageSize") Integer pageSize);

    int deleteLaboratoryType(@Param("ids") List ids);

    List<LaboratoryType> getAllLaboratoryType();
}
