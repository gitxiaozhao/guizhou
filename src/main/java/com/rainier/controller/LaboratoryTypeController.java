package com.rainier.controller;

import com.rainier.model.LaboratoryType;
import com.rainier.service.LaboratoryTypeService;
import com.rainier.util.AddLog;
import com.rainier.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
* @描述 实验室类型
* @参数注释：
* @创建人  wyz
* @创建时间  2020/4/7
*/
@Controller
@RequestMapping("laboratoryType")
public class LaboratoryTypeController {
    @Autowired
    private LaboratoryTypeService laboratoryTypeService;

    /**
    * @描述 添加或者修改实验室类型
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/4/7
    */
    @AddLog(title = "添加或者修改实验室类型")
    @RequestMapping("addOrUpdateLaboratoryType")
    @ResponseBody
    public Result addOrUpdateLaboratoryType(@RequestBody LaboratoryType laboratoryType){
        return laboratoryTypeService.addOrUpdateLaboratoryType(laboratoryType);
    }

    /**
    * @描述 查看实验室类型详情
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/4/7
    */
    @RequestMapping("getLaboratoryTypeById")
    @ResponseBody
    public Result getLaboratoryTypeById(@RequestBody Map map){
        return laboratoryTypeService.getLaboratoryTypeById(map);
    }

    /**
    * @描述 查询实验室类型列表
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/4/7
    */
    @RequestMapping("getLaboratoryTypeList")
    @ResponseBody
    public Result getLaboratoryTypeList(@RequestBody Map map){
        return laboratoryTypeService.getLaboratoryTypeList(map);
    }

    /**
    * @描述 批量删除实验室类型
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/4/8
    */
    @AddLog(title = "删除实验室类型")
    @RequestMapping("deleteLaboratoryType")
    @ResponseBody
    public Result deleteLaboratoryType(@RequestBody Map map){
        return laboratoryTypeService.deleteLaboratoryType(map);
    }

    /**
    * @描述 查询实验室类型列表（不带分页）
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/4/8
    */
    @RequestMapping("getAllLaboratoryType")
    @ResponseBody
    public Result getAllLaboratoryType(){
        return laboratoryTypeService.getAllLaboratoryType();
    }
}
