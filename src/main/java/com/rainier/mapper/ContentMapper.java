package com.rainier.mapper;

import com.rainier.model.Content;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ContentMapper {
    List<Map> getAllUrl();

    void addContent(@Param("content") Content content);

    void updateContent(@Param("content") Content content);

    Integer getContentListCount(@Param("title") String title,@Param("labelId") String labelId);

    List<Content> getContentList(@Param("title") String title,@Param("labelId") String labelId,
                                 @Param("pageStart") Integer pageStart,@Param("pageSize") Integer pageSize);

    void deleteLabel(@Param("contentids") List contentids);

    Content getContentById(@Param("contentid")Integer contentid);
}
