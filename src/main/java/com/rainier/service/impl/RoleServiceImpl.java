package com.rainier.service.impl;

import com.rainier.mapper.RoleMapper;
import com.rainier.model.Role;
import com.rainier.model.User;
import com.rainier.service.RoleService;
import com.rainier.util.Page;
import com.rainier.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {


    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Result addRole(Map map) {
        Role role = new Role();
        if (map.get("id").toString().equals("") || map.get("id") == null){
            role.setId(null);
        }else {
            role.setId(Integer.parseInt(map.get("id").toString()));
        }

        role.setName(map.get("name").toString());
        role.setDescribe(map.get("describe").toString());
        //有id修改无id添加
        if (role.getId() == null){
            roleMapper.addRole(role);
        }else{
            roleMapper.updateRole(role);
        }

        return Result.success();
    }

    @Override
    public Result addRoleMenu(Map map) {
        //删除关于此角色的旧权限
        roleMapper.deleteRoleMenu(Integer.parseInt(map.get("roleid").toString()));

        //添加指定权限
        List menuids = (List) map.get("menuids");
        roleMapper.addRoleMenu(Integer.parseInt(map.get("roleid").toString()),menuids);

        return Result.success();
    }

    @Override
    public Result deleteRole(Map map) {
        List roleids = (List) map.get("roleid");
        if (roleids.size() > 0 && roleids != null){
            //删除角色
            roleMapper.deleteRole(roleids);
            //删除拥有当前角色的用户关联
            roleMapper.deleteUserRoleByRoleid(roleids);
            //删除拥有当前角色的权限关联
            roleMapper.deleteRoleMenus(roleids);

            return Result.success();
        }else {
            return Result.error("角色id为空");
        }

    }

    @Override
    public Result getRoleList(Map map) {
        String name = map.get("name").toString();
        Integer pageIndex = Integer.parseInt(map.get("pageIndex").toString());//当前页数
        Integer pageSize = Integer.parseInt(map.get("pageSize").toString());//每页条数
        Page<Role> page = new Page<Role>();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        page.setTotalRecords(roleMapper.queryUserCount(name));
        page.setList(roleMapper.queryUserList(name, ((page.getPageIndex() - 1) * page.getPageSize()), page.getPageSize()));
        return Result.success(page);
    }

    @Override
    public Result queryRoleByid(Map map) {
        List list = roleMapper.queryRoleByid(map.get("roleid").toString());
        return Result.success(list);
    }
}
