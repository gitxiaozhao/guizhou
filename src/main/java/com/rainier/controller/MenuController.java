package com.rainier.controller;

import com.rainier.model.Menu;
import com.rainier.service.MenuService;
import com.rainier.util.AddLog;
import com.rainier.util.Result;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* @描述 权限管理
* @参数注释：
* @创建人  wyz
* @创建时间  2019/11/26
*/
@Controller
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuService menuService;


    /**
    * @描述 查询权限列表
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/11/26
    */
    @RequestMapping("queryMenuList")
    @ResponseBody
    public Result queryMenuList(@RequestBody Map map){
        return menuService.queryMenuList(map);
    }


    /**
    * @描述 添加时查询“父级”字段的值
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/11/26
    */
    @RequestMapping("queryMenu")
    @ResponseBody
    public Result queryMenu(){
        return menuService.queryMenu();
    }


    /**
    * @描述 添加或者修改
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/11/26
    */
    @AddLog(title = "添加或者修改角色")
    @RequestMapping("addMenuOrUpdate")
    @ResponseBody
    public Result addMenuOrUpdate(@RequestBody Map map){
        return menuService.addMenuOrUpdate(map);
    }

    /**
    * @描述 树形权限展示
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/11/26
    */
    @RequestMapping(value="/moduleTree",method= RequestMethod.POST)
    @ResponseBody
    public Result moduleTree(){
        List<Menu> modules=menuService.getModuleByLevelType();
        return new Result("200","success",getList(modules,69));
    }
    private List<Map<String,Object>> getList(List<Menu> modules,Integer id){
        List<Map<String,Object>> list=new ArrayList<>();

        modules.forEach(module -> {
            Integer pid=module.getParentid();
            pid=pid==null?0:pid;
            if (pid.equals(id)){
                Map<String,Object> map=new HashedMap();

                map.put("id",module.getId());
                map.put("label",module.getName());
                List<Map<String,Object>> maps=getList(modules,module.getId());
                if (maps.size()!=0)
                    map.put("children",maps);
                list.add(map);
            }
        });

        return list;
    }

    /**
    * @描述 删除权限
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/11/26
    */
    @AddLog(title = "删除权限")
    @RequestMapping("deleteMenu")
    @ResponseBody
    public Result deleteMenu(@RequestBody Map map){
        return menuService.deleteMenu(map);
    }

    /**
    * @描述 树形展示回显
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/11/27
    */
    @RequestMapping("getUserMenu")
    @ResponseBody
    public Result getUserMenu(@RequestBody Map map){
        return menuService.getUserMenu(map);
    }

    /**
    * @描述 查看权限详情
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/11/28
    */
    @RequestMapping("queryMenuByid")
    @ResponseBody
    public Result queryMenuByid(@RequestBody Map map) throws ParseException {
        return menuService.queryMenuByid(map);
    }
}
