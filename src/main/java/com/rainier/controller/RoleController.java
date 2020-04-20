package com.rainier.controller;

import com.rainier.service.RoleService;
import com.rainier.util.AddLog;
import com.rainier.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
* @描述 角色管理
* @参数注释：
* @创建人  wyz
* @创建时间  2019/11/22
*/
@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    /**
    * @描述 添加或修改角色
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/11/22
    */
    @AddLog(title = "添加或者修改角色")
    @RequestMapping("addRole")
    @ResponseBody
    public Result addRole(@RequestBody Map map){
        return roleService.addRole(map);
    }

    /**
    * @描述 给角色添加权限
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/11/23
    */
    @AddLog(title = "给角色添加权限")
    @RequestMapping("addRoleMenu")
    @ResponseBody
    public Result addRoleMenu(@RequestBody Map map){
        return roleService.addRoleMenu(map);
    }


    /**
    * @描述 删除角色
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/11/26
    */
    @AddLog(title = "删除角色")
    @RequestMapping("deleteRole")
    @ResponseBody
    public Result deleteRole(@RequestBody Map map){
        return roleService.deleteRole(map);
    }

    /**
    * @描述 查询角色列表
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/11/26
    */
    @RequestMapping("getRoleList")
    @ResponseBody
    public Result getRoleList(@RequestBody Map map){
        return roleService.getRoleList(map);
    }

    /**
    * @描述 查看角色详情
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/11/28
    */
    @RequestMapping("queryRoleByid")
    @ResponseBody
    public Result queryRoleByid(@RequestBody Map map){
        return roleService.queryRoleByid(map);
    }

}
