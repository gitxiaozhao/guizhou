package com.rainier.service;

import com.rainier.model.Menu;
import com.rainier.util.Result;

import java.util.List;
import java.util.Map;

public interface MenuService {
    Result queryMenuList(Map map);

    Result queryMenu();

    Result addMenuOrUpdate(Map map);

    List<Menu> getModuleByLevelType();

    Result deleteMenu(Map map);

    Result getUserMenu(Map map);

    Result queryMenuByid(Map map);
}
