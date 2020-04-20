package com.rainier.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rainier.service.LogService;
import com.rainier.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Map;

/**
* @描述 日志管理
* @参数注释：
* @创建人  wyz
* @创建时间  2019/12/2
*/
@Controller
@RequestMapping("log")
public class LogController{
    @Autowired
    private LogService logService;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @RequestMapping(value = "/selByPage",method = RequestMethod.POST)
    @ResponseBody
    public Result selByPage(HttpServletRequest request, @RequestBody Map map) throws ParseException {

       /* Map<String,Object> map= new HashMap<>();
        map.put("pageIndex",pageIndex);
        map.put("pageSize",pageSize);
        Map<String,Object> params=new HashMap<>();
        if (StringUtil.equalsIsNullString(userid)) {
            params.put("userid,=", userid);
        }
        if (StringUtil.equalsIsNullString(tablename)) {
            params.put("tablename,like", tablename);
        }
        if (StringUtil.equalsIsNullString(starttime)) {
            starttime+=" 0:0:0";
            params.put("date,>", Timestamp.valueOf(starttime));
        }
        if (StringUtil.equalsIsNullString(endtime)) {
            endtime+=" 0:0:0";
            params.put("date,<", Timestamp.valueOf(endtime));
        }
        if (params.size()>0)
            map.put("Criteria",params);
            return logService.selectPage(map);*/

        return logService.selByPage(map);
    }
}
