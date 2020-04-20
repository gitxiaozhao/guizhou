package com.rainier.controller;

import com.rainier.service.DataClassifyService;
import com.rainier.util.AddLog;
import com.rainier.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
* @描述 数据分类管理
* @参数注释：
* @创建人  wyz
* @创建时间  2020/2/20
*/
@Controller
@RequestMapping("dataClassify")
public class DataClassifyController {


    @Autowired
    private DataClassifyService dataClassifyService;

    /**
    * @描述 添加或者修改数据分类
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/2/20
    */
    @AddLog(title = "添加或者修改数据分类")
    @RequestMapping("addOrUpdateDataClassify")
    @ResponseBody
    public Result addOrUpdateDataClassify(@RequestBody Map map){
        return dataClassifyService.addOrUpdateDataClassify(map);
    }


    /**
    * @描述 询数据分类列表
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/2/21
    */
    @RequestMapping("getDataClassify")
    @ResponseBody
    public Result getDataClassify(@RequestBody Map map){
        return dataClassifyService.getDataClassify(map);
    }


    /**
    * @描述 删除数据分类
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/2/21
    */
    @AddLog(title = "删除数据分类")
    @RequestMapping("deleteDataClassify")
    @ResponseBody
    public Result deleteDataClassify(@RequestBody Map map){
        return dataClassifyService.deleteDataClassify(map);
    }

    /**
    * @描述 查看详情
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/2/21
    */
    @RequestMapping("getDataClassifyById")
    @ResponseBody
    public Result getDataClassifyById(@RequestBody Map map){
        return dataClassifyService.getDataClassifyById(map);
    }
}
