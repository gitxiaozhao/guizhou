package com.rainier.service.impl;

import com.rainier.mapper.UserMapper;
import com.rainier.model.User;
import com.rainier.service.UserService;
import com.rainier.util.AddLog;
import com.rainier.util.MD5Util;
import com.rainier.util.Page;
import com.rainier.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result addUser(Map map) throws ParseException {
        User user = new User();
        if (map.get("id").toString().equals("") || map.get("id") == null){
            user.setId(null);
        }else {
            user.setId(Integer.parseInt(map.get("id").toString()));
        }

        user.setName(map.get("name").toString());

        user.setPassword(map.get("password").toString());
        user.setState(Integer.parseInt(map.get("state").toString()));

        user.setEmail(map.get("email").toString());
        user.setPhone(map.get("phone").toString());

        //有id修改无id添加
        if (user.getId() == null){
            //添加前先做验证是否重复
            User u = userMapper.getUserbyName(user);
            if (u == null){
                userMapper.addUser(user);
                return Result.success("添加成功");
            }else{
                return Result.error("此用户已存在");
            }

        }else{
            userMapper.updateUser(user);
            return Result.success("修改成功");
        }



    }

    @Override
    public void addUserRole(Map map) {

        //删除关于此用户的旧角色
        userMapper.deleteUserRoleByuUserId(Integer.parseInt(map.get("userid").toString()));

        //给用户配置新的角色
        List roleids = (List) map.get("roleids");
        userMapper.addUserRole(Integer.parseInt(map.get("userid").toString()),roleids);
    }

    @Override
    public Result deleteUser(Map map) {
        List userids = (List) map.get("userid");
        if (userids != null && userids.size() > 0){
            //删除用户
            userMapper.deleteUsers(userids);
            //删除用户的角色
            userMapper.deleteUserRoleByuUserIds(userids);
            return Result.success("删除成功");
        }else {
            return Result.error("用户id为空");
        }


    }

    @Override
    public Result queryUserList(Map map) {

        String name = map.get("name").toString();
        Integer pageIndex = Integer.parseInt(map.get("pageIndex").toString());//当前页数
        Integer pageSize = Integer.parseInt(map.get("pageSize").toString());//每页条数
        Page<User> page = new Page<User>();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        page.setTotalRecords(userMapper.queryUserCount(name));
        page.setList(userMapper.queryUserList(name, ((page.getPageIndex() - 1) * page.getPageSize()), page.getPageSize()));
        return Result.success(page);
    }

    @Override
    public Result queryUserByid(Map map) throws ParseException {
        List user = userMapper.queryUserByid(Integer.parseInt(map.get("userid").toString()));
        return Result.success(user);
    }

    @Override
    public Result updatePassword(Map map) {
        String password = map.get("password").toString();
        userMapper.updatePassword(Integer.parseInt(map.get("userid").toString()),password);
        return Result.success();
    }

    @Override
    public Result updateState(Map map) {
        userMapper.updateState(Integer.parseInt(map.get("state").toString()),map.get("userid").toString());
        return Result.success();
    }

    @Override
    public Result getRole() {
        List list = userMapper.getRole();
        return Result.success(list);
    }

    @Override
    public Result getRoleByUserid(Map map) {
        List list = userMapper.getRoleByUserid(Integer.parseInt(map.get("userid").toString()));
        return Result.success(list);
    }


}
