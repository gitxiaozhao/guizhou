package com.rainier.mapper;


import com.rainier.model.Url;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface UrlDao {

    //插入
    @Insert({"insert into imgurl (name,lujing,url) values (#{name},#{lujing},#{url})"})
    public int insertUrl(@Param("name")String name,@Param("lujing")String lujing,@Param("url")String url);

    //查询
    @Select("select * from imgurl")
    public List<Url> selectShipin();

    void deleteImgurlByUrl(@Param("urlList") List<String> urlList);

    List<Map> getImgurlLujing();

    List<Map> getImgUrlByUrl(@Param("url") String url);

    void deleteImgUrlById(@Param("id")Integer id);
}

