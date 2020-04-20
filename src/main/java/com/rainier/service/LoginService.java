package com.rainier.service;

import com.rainier.model.Menu;
import com.rainier.util.Result;

import java.util.List;

public interface LoginService {
    Result getUserByLogin(String name, String password);

    Result updateLoginTime(Integer id);

    List<Menu> getModuleByLevelType();

    List selModulesAll(Integer id);
}
