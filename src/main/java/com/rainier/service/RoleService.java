package com.rainier.service;

import com.rainier.util.Result;

import java.util.Map;

public interface RoleService {
    Result addRole(Map map);

    Result addRoleMenu(Map map);

    Result deleteRole(Map map);

    Result getRoleList(Map map);

    Result queryRoleByid(Map map);
}
