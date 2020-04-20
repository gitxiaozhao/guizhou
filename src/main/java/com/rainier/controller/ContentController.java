package com.rainier.controller;

import com.rainier.model.Content;
import com.rainier.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rainier.util.Result;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
* @描述 内容发布
* @参数注释：
* @创建人  wyz
* @创建时间  2019/12/10
*/
@Controller
@RequestMapping("content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    /**
    * @描述 新增或者修改内容
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/12/10
    */
    @ResponseBody
    @RequestMapping("addContent")
    public Result addContent(@RequestBody Map map){
        return contentService.addContent(map);
    }

    /**
    * @描述 查询内容列表
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/12/10
    */
    @ResponseBody
    @RequestMapping("getContentList")
    public Result getContentList(@RequestBody Map map){
        return contentService.getContentList(map);
    }

    /**
    * @描述 内容删除
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/12/10
    */
    @ResponseBody
    @RequestMapping("deleteContent")
    public Result deleteContent(@RequestBody Map map){
        return contentService.deleteContent(map);
    }


    /**
    * @描述 查看详情
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/12/10
    */
    @ResponseBody
    @RequestMapping("getContentById")
    public Result getContentById(@RequestBody Map map){
        return contentService.getContentById(map);
    }
}
