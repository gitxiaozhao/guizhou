package com.rainier.controller;

import com.rainier.model.User;
import com.rainier.service.UserService;
import com.rainier.util.AddLog;
import com.rainier.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
* @描述 用户管理
* @参数注释：
* @创建人  wyz
* @创建时间  2019/11/21
*/
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
    * @描述 添加用户或者修改用户
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/11/21
    */
    @AddLog(title = "添加用户")
    @RequestMapping("addUser")
    @ResponseBody
    public Result addUser(@RequestBody Map map) throws ParseException {
        return userService.addUser(map);
    }


    /**
    * @描述 给用户配置角色
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/11/22
    */
    @AddLog(title = "给用户配置角色")
    @RequestMapping("addUserRole")
    @ResponseBody
    public Result addUserRole(@RequestBody Map map){

        userService.addUserRole(map);
        return Result.success();
    }

    /**
    * @描述 删除用户
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/11/22
    */
    @AddLog(title = "删除用户")
    @RequestMapping("deleteUser")
    @ResponseBody
    public Result deleteUser(@RequestBody Map map){


        return userService.deleteUser(map);
    }

    /**
    * @描述 查询用户列表
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/11/22
    */
    @RequestMapping("queryUserList")
    @ResponseBody
    public Result queryUserList(@RequestBody Map map){
        return userService.queryUserList(map);
    }


    /**
    * @描述 查看用户详情
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/11/27
    */
    @RequestMapping("queryUserByid")
    @ResponseBody
    public Result queryUserByid(@RequestBody Map map) throws ParseException {
        return userService.queryUserByid(map);
    }

    /**
    * @描述 修改密码
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/11/27
    */
    @AddLog(title = "修改密码")
    @RequestMapping("updatePassword")
    @ResponseBody
    public Result updatePassword(@RequestBody Map map){
        return userService.updatePassword(map);
    }

    /**
    * @描述 修改用户状态
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/11/28
    */
    @AddLog(title = "修改用户状态")
    @RequestMapping("updateState")
    @ResponseBody
    public Result updateState(@RequestBody Map map){
        return userService.updateState(map);
    }

    /**
    * @描述 查询所有角色
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/11/28
    */
    @RequestMapping("getRole")
    @ResponseBody
    public Result getRole(){
        return userService.getRole();
    }

    /**
    * @描述 查询用户已经拥有的角色
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/11/28
    */
    @RequestMapping("getRoleByUserid")
    @ResponseBody
    public Result getRoleByUserid(@RequestBody Map map){
        return userService.getRoleByUserid(map);
    }
    
}
