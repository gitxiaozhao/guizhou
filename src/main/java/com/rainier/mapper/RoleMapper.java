package com.rainier.mapper;

import com.rainier.model.Role;
import com.rainier.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {
    void addRole(@Param("role") Role role);

    void updateRole(@Param("role") Role role);

    void deleteRoleMenu(@Param("roleid") Integer roleid);

    void addRoleMenu(@Param("roleid") Integer roleid,@Param("menuids") List menuids);

    void deleteRole(@Param("roleids") List roleids);

    void deleteUserRoleByRoleid(@Param("roleids") List roleids);

    Integer queryUserCount(@Param("name") String name);

    List<Role> queryUserList(@Param("name") String name, Integer pageStart, Integer pageSize);

    List queryRoleByid(@Param("roleid") String roleid);

    void deleteRoleMenus(@Param("roleids") List roleids);
}
