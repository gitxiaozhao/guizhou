package com.rainier.service;

import com.rainier.util.Result;

import java.text.ParseException;
import java.util.Map;

public interface UserService {
    Result addUser(Map map) throws ParseException;

    void addUserRole(Map map);

    Result deleteUser(Map map);

    Result queryUserList(Map map);

    Result queryUserByid(Map map) throws ParseException;

    Result updatePassword(Map map);

    Result updateState(Map map);

    Result getRole();

    Result getRoleByUserid(Map map);
}
