package com.rainier.mapper;

import com.rainier.model.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper {
    Integer queryMenuCount(@Param("name") String name);

    List<Menu> queryMenuList(@Param("name") String name,@Param("pageStart") Integer pageStart,@Param("pageSize") Integer pageSize);

    List queryMenu();

    void addMenu(@Param("menu") Menu menu);

    void updateMenu(@Param("menu") Menu menu);

    List<Menu> getModuleByLevelType();

    void deleteMenu(@Param("menuids") List menuids);

    void deleteRoleMenu(@Param("menuids") List menuids);

    List getUserMenu(@Param("roleid") Integer roleid);

    List queryMenuByid(@Param("menuid") Integer menuid);
}
