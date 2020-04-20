package com.rainier.controller;

import com.rainier.service.DataTypeService;
import com.rainier.util.AddLog;
import com.rainier.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
* @描述 数据类型
* @参数注释：
* @创建人  wyz
* @创建时间  2020/2/18
*/
@Controller
@RequestMapping("dataType")
public class DataTypeConeroller {


    @Autowired
    private DataTypeService dataTypeService;

    /**
    * @描述
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/2/18
    */
    @AddLog(title = "添加或者修改数据类型")
    @RequestMapping("addOrUpdateDateType")
    @ResponseBody
    public Result addOrUpdateDateType(@RequestBody Map map){
        return dataTypeService.addOrUpdateDateType(map);
    }

    /**
    * @描述 查询数据类型列表
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/2/18
    */
    @RequestMapping("getDataType")
    @ResponseBody
    public Result getDataType(@RequestBody Map map){
        return dataTypeService.getDataType(map);
    }


    /**
    * @描述 删除数据类型
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/2/18
    */
    @AddLog(title = "删除数据类型")
    @RequestMapping("deleteDataType")
    @ResponseBody
    public Result deleteDataType(@RequestBody Map map){
        return dataTypeService.deleteDataType(map);
    }

    /**
     * @描述 查看详情
     * @参数注释：
     * @创建人  wyz
     * @创建时间  2019/12/3
     */
    @RequestMapping("getDataTypeById")
    @ResponseBody
    public Result getDataTypeById(@RequestBody Map map){
        return dataTypeService.getDataTypeById(map);
    }
}
