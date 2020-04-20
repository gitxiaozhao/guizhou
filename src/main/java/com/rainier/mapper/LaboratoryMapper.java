package com.rainier.mapper;

import com.rainier.model.Laboratory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface LaboratoryMapper {
    int updateLaboratory(@Param("laboratory") Laboratory laboratory);

    int addLaboratory(@Param("laboratory") Laboratory laboratory);

    List<Map> getAllUrl();

    Integer queryLaboratoryCount(@Param("key") String key);

    List<Map> queryLaboratoryList(@Param("key") String key, @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);

    Map getLaboratoryById(@Param("laboratory") Laboratory laboratory);

    int deleteLaboratoryByIds(@Param("ids") List ids);

    List<Map> getLaboratoryListByRecommend1(@Param("ltid") String ltid);
}
