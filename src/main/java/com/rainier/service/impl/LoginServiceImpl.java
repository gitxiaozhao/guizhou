package com.rainier.service.impl;

import com.rainier.mapper.LoginMapper;
import com.rainier.model.Menu;
import com.rainier.model.User;
import com.rainier.service.LoginService;
import com.rainier.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public Result getUserByLogin(String name, String password) {
        User user = loginMapper.getUserByLogin(name,password);
        return Result.success(user);
    }

    @Override
    public Result updateLoginTime(Integer id) {
        User user = new User();
        user.setId(id);
        user.setLast_login_time(new Timestamp(System.currentTimeMillis()));
        int i = loginMapper.updateByPrimaryKeySelective(user);
        if (i > 0) {
            return new Result("200", "success");
        } else {
            return new Result("500", "error");
        }

    }

    @Override
    public List<Menu> getModuleByLevelType() {
        return loginMapper.getModuleByLevelType();
    }

    @Override
    public List selModulesAll(Integer id) {
        return loginMapper.selModulesAll(id);
    }
}
