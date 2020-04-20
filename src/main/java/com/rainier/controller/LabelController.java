package com.rainier.controller;

import com.rainier.service.LabelService;
import com.rainier.util.AddLog;
import com.rainier.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.Map;

/**
* @描述 内容标签管理
* @参数注释：
* @创建人  wyz
* @创建时间  2019/12/2
*/
@Controller
@RequestMapping("label")
public class LabelController {

    @Autowired
    private LabelService labelService;


    /**
    * @描述 添加或者修改标签
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/12/2
    */
    @AddLog(title = "添加或者修改标签")
    @RequestMapping("addLabel")
    @ResponseBody
    public Result addLabel(@RequestBody Map map){
        return labelService.addLabel(map);
    }

    /**
    * @描述 查询标签
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/12/2
    */
    @RequestMapping("getLabel")
    @ResponseBody
    public Result getLabel(@RequestBody Map map){
        return labelService.getLabel(map);
    }

    /**
    * @描述 删除标签
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/12/3
    */
    @AddLog(title = "删除标签")
    @RequestMapping("deleteLabel")
    @ResponseBody
    public Result deleteLabel(@RequestBody Map map){
        return labelService.deleteLabel(map);
    }


    /**
    * @描述 查询所有标签（不分页）
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/12/3
    */
    @RequestMapping("getAllLabel")
    @ResponseBody
    public Result getAllLabel(){
        return labelService.getAllLabel();
    }

    /**
    * @描述 查看详情
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/12/3
    */
    @RequestMapping("getLabelById")
    @ResponseBody
    public Result getLabelById(@RequestBody Map map){
        return labelService.getLabelById(map);
    }
}
