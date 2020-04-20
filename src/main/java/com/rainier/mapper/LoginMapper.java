package com.rainier.mapper;

import com.rainier.model.Menu;
import com.rainier.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LoginMapper {
    User getUserByLogin(@Param("name") String name, @Param("password") String password);

    int updateByPrimaryKeySelective(@Param("user") User user);

    List<Menu> getModuleByLevelType();

    List selModulesAll(@Param("id") Integer id);
}
