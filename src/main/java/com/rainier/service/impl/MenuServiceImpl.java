package com.rainier.service.impl;

import com.rainier.mapper.MenuMapper;
import com.rainier.model.Menu;
import com.rainier.service.MenuService;
import com.rainier.util.Page;
import com.rainier.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Result queryMenuList(Map map) {
        String name = map.get("name").toString();
        Integer pageIndex = Integer.parseInt(map.get("pageIndex").toString());//当前页数
        Integer pageSize = Integer.parseInt(map.get("pageSize").toString());//每页条数
        Page<Menu> page = new Page<Menu>();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        page.setTotalRecords(menuMapper.queryMenuCount(name));
        page.setList(menuMapper.queryMenuList(name, ((page.getPageIndex() - 1) * page.getPageSize()), page.getPageSize()));
        return Result.success(page);
    }

    @Override
    public Result queryMenu() {
        List list = menuMapper.queryMenu();
        return Result.success(list);
    }

    @Override
    public Result addMenuOrUpdate(Map map) {
        Menu menu = new Menu();
        if (map.get("id").toString().equals("") || map.get("id") == null){
            menu.setId(null);
        }else {
            menu.setId(Integer.parseInt(map.get("id").toString()));
        }
        menu.setName(map.get("name").toString());
        menu.setParentid(Integer.parseInt(map.get("parentid").toString()));
        menu.setParentName(map.get("parentName").toString());
        menu.setIsDisplay(Integer.parseInt(map.get("isDisplay").toString()));
        menu.setDisplayOrder(Integer.parseInt(map.get("displayOrder").toString()));
        menu.setUrl(map.get("url").toString());
        menu.setDescription(map.get("description").toString());
        menu.setLevel(Integer.parseInt(map.get("level").toString()));

        //有id修改无id添加
        if (menu.getId() == null){
            menuMapper.addMenu(menu);
        }else{
            menuMapper.updateMenu(menu);
        }

        return Result.success();
    }

    @Override
    public List<Menu> getModuleByLevelType() {
        return menuMapper.getModuleByLevelType();
    }

    @Override
    public Result deleteMenu(Map map) {
        List menuids = (List) map.get("menuid");
        if (menuids.size() > 0 && menuids != null){
            //删除权限
            menuMapper.deleteMenu(menuids);
            //删除角色和此权限的关联
            menuMapper.deleteRoleMenu(menuids);
            return Result.success();
        }else {
            return Result.error("权限id为空");
        }

    }

    @Override
    public Result getUserMenu(Map map) {
        List list = menuMapper.getUserMenu(Integer.parseInt(map.get("roleid").toString()));
        return Result.success(list);
    }

    @Override
    public Result queryMenuByid(Map map) {
        List list = menuMapper.queryMenuByid(Integer.parseInt(map.get("menuid").toString()));
        return Result.success(list);
    }


}
