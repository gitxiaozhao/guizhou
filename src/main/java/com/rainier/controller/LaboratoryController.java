package com.rainier.controller;

import com.rainier.model.Laboratory;
import com.rainier.service.LaboratoryService;
import com.rainier.util.AddLog;
import com.rainier.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
* @描述 实验室
* @参数注释：
* @创建人  wyz
* @创建时间  2020/4/1
*/
@Controller
@RequestMapping("/laboratory")
public class LaboratoryController {

    @Autowired
    private LaboratoryService laboratoryService;

    /**
    * @描述 添加或者修改实验室信息
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/4/1
    */
    @AddLog(title = "加或者修改实验室信息")
    @RequestMapping("addLaboratory")
    @ResponseBody
    public Result addLaboratory(@RequestBody Laboratory laboratory){
        return laboratoryService.addLaboratory(laboratory);

    }

    /**
    * @描述 查询实验室数据列表
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/4/1
    */
    @RequestMapping("getLaboratoryList")
    @ResponseBody
    public Result getLaboratoryList(@RequestBody Map map){
        return laboratoryService.getLaboratoryList(map);

    }

    /**
    * @描述 查看详情
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/4/2
    */
    @RequestMapping("getLaboratoryById")
    @ResponseBody
    public Result getLaboratoryById(@RequestBody Laboratory laboratory){
        return laboratoryService.getLaboratoryById(laboratory);

    }

    /**
    * @描述 批量删除
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/4/2
    */
    @AddLog(title = "批量删除实验室信息")
    @RequestMapping("deleteLaboratoryByIds")
    @ResponseBody
    public Result deleteLaboratoryByIds(@RequestBody Map map){
        return laboratoryService.deleteLaboratoryByIds(map);

    }

    /**
    * @描述 轮播图展示
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/4/3
    */
    @RequestMapping("getLaboratoryListByRecommend1")
    @ResponseBody
    public Result getLaboratoryListByRecommend1(@RequestBody Map map){
        return laboratoryService.getLaboratoryListByRecommend1(map);

    }
}
